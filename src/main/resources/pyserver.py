#!/usr/bin/python
#coding:utf-8
import socket
import itchat
import json
import time
import inspect
import sys
import base64
import io
from itchat.returnvalues import ReturnValue
from threading import Thread

server_sock = None
client_sock = None
client_addr = None
socket_fd = None


def start_server():
    global server_sock, client_sock, client_addr, socket_fd
    socket.setdefaulttimeout(30)
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
    method = getattr(itchat, cmd)
    args, varargs, keywords, defaults = inspect.getargspec(method)

    if not hasattr(method, '__call__'):
        return

    selector = 'itchat.' + cmd + '('

    argsList = []
    for (key, value) in argsDict.items():
        if key not in args:
            continue
        else:
            argsList.append(key + '=' + value + '')
    selector += ','.join(argsList) +  ')'
    print('method: ' + selector)
    response = eval(selector)
    if response == None:
        send_string("{\"Cmd\":\"" + cmd + "\"}")
    elif isinstance(response, basestring) or isinstance(response, dict) or isinstance(response, list):
        send_string("{\"Cmd\":\"" + cmd + "\",\"Args\":" + json.dumps(response) + "}")
    elif isinstance(response, io.BytesIO):
        send_string("{\"Cmd\":\"" + cmd + "\",\"Args\":\"" + base64.b64encode(response.getvalue()) + "\"}")
    elif isinstance(response, ReturnValue):
        send_string("{\"Cmd\":\"" + cmd + "\",\"Args\":" + json.dumps(response['BaseResponseDO']) + "}")
    else:
        print('response: ' + str(response))
        send_string("{\"Cmd\":\"" + cmd + "\"}")
    # send_string("{\"Cmd\":\"" + cmd + "\",\"Args\":{\"Method\":\"" + selector + "\"}}")


def handle_receive(recv_data):
    if len(recv_data) > 0:
        dic = json.loads(recv_data)
        switch_cmd(dic['Cmd'], dic['Args'])

def login_qrCallback(uuid, status, qrcode):
    data = {'uuid': uuid, 'status': status, 'qrcode': base64.b64encode(qrcode)}
    send_string("{'Cmd':'login_qrCallback','Args':" + json.dumps(data) + "}")

def login_loginCallback():
    send_string("{'Cmd':'login_loginCallback'}")

def login_exitCallback():
    send_string("{'Cmd':'login_exitCallback'")

def loginStatus_loginCallback():
    send_string("{'Cmd':'loginStatus_loginCallback'}")

def loginStatus_exitCallback():
    send_string("{'Cmd':'loginStatus_exitCallback'")



# itchat.auto_login(hotReload=True)
start_server()
stop_server()
