#!/usr/bin/python
#coding:utf-8
import socket
import itchat
import json
import time
import inspect
import sys
from threading import Thread
from enum import Enum

CmdTypeMapper = {'1': 'login',
               '2': 'auto_login',
               '3': 'get_QRuuid',
               '4': 'get_QR',
               '5': 'check_login',
               '6': 'web_init',
               '7': 'show_mobile_login',
               '8': 'start_receiving',
               '9': 'get_msg',
               '10': 'logout',
               '11': 'update_chatroom',
               '12': 'update_friend',
               '13': 'get_contact',
               '14': 'get_friends',
               '15': 'get_chatrooms',
               '16': 'get_mps',
               '17': 'set_alias',
               '18': 'set_pinned',
               '19': 'add_friend',
               '20': 'get_head_img',
               '21': 'create_chatroom',
               '22': 'set_chatroom_name',
               '23': 'delete_member_from_chatroom',
               '24': 'add_member_into_chatroom',
               '25': 'send_raw_msg',
               '26': 'send_msg',
               '27': 'upload_file',
               '28': 'send_file',
               '29': 'send_image',
               '30': 'send_video',
               '31': 'send',
               '32': 'revoke',
               '33': 'dump_login_status',
               '34': 'load_login_status',
               '35': 'configured_reply',
               '36': 'msg_register',
               '37': 'run',
               '38': 'search_friends',
               '39': 'search_chatrooms',
               '40': 'search_mps'
               }
server_sock = None
client_sock = None
client_addr = None
socket_fd = None


def start_server():
    global server_sock, client_sock, client_addr, socket_fd
    socket.setdefaulttimeout(5)
    try:
        server_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except socket.error, e:
        print('Strange error creating socket:%s' % e)
        sys.exit(1)

    try:
        server_sock.bind(('localhost', 8001))
    except socket.error, e:
        print('Strange error bind socket:%s' % e)
        sys.exit(1)

    print('Bind successfully')

    try:
        server_sock.listen(5)
    except socket.error, e:
        print('Strange error begin to listen:%s' % e)
        sys.exit(1)

    print('Server - Begin to listened')

    while True:
        try:
            client_sock, client_addr = server_sock.accept()
        except socket.error, e:
            continue

        try:
            socket_fd = client_sock.makefile('rw', 0)
        except socket.error, e:
            print('Makefile error:%s' % e)
            close_connection()
            continue

        print('Server - have accepted')

        communicate()


def stop_server():
    global server_sock, client_sock
    client_sock.close;
    server_sock.close;


def close_connection():
    global client_sock
    try:
        client_sock.close()
    except socket.error, e:
        print('Error close socket:%s' % e)


def flush_socket():
    global socket_fd
    try:
        socket_fd.flush()
    except socket.error, e:
        print('Error flush the buffer:%s' % e)
        return False
    else:
        return True


def communicate():
    global client_sock
    while True:
        buf = ''
        while True:
            try:
                buf += client_sock.recv(1024)
            except socket.error, e:
                print('Error receiving data:%s' % e)
                close_connection()
                return
            if not len(buf):
                print('Socket has been closed!')
                close_connection()
                return
            if '\r\n' in buf:
                buf = buf.rstrip('\r\n')
                break

        for index, value in enumerate(buf.split('\r\n')):

            if value.startswith('KeepAlive'):
                print('The receiving data is heartbeat')
                continue

            print('The receiving data is', value)

            handle_receive(value)


def send_string(string):
    global client_sock
    try:
        print('The sending data is', string)
        client_sock.send(string + '\r\n')
    except socket.error, e:
        print('Error sending data:%s' % e)
        close_connection()
        return

    if not flush_socket():
        close_connection()
        return

def switch_cmd(cmd, argsDict):
    method = getattr(itchat, CmdTypeMapper[cmd])
    args, varargs, keywords, defaults = inspect.getargspec(method)
    defaults_args = dict(zip(args[-len(defaults):], defaults))

    if not hasattr(method, '__call__'):
        return

    selector = 'itchat.' + CmdTypeMapper[cmd] + '('

    argsList = []
    for (key, value) in argsDict.items():
        if key not in args:
            continue
        if key not in defaults_args:
            argsList.append(key + '=\'' + value + '\'')
        elif defaults_args[key] in (True, False, None):
            argsList.append(key + '=' + value + '')
        else:
            argsList.append(key + '=\'' + value + '\'')
    selector += ','.join(argsList) +  ')'
    print('method: ' + selector)
    # eval(method)

def handle_receive(recv_data):
    if len(recv_data) > 0:
        dic = json.loads(recv_data)
        switch_cmd(dic['Cmd'], dic['Args'])


# itchat.auto_login(hotReload=True)
start_server()
stop_server()
