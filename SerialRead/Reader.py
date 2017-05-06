import serial
import time
ser = serial.Serial('COM8', 9600, timeout=0)
cha = '\n'

str1 = b'hello world'
str2 = bytes("b'hello world", decode="UTF-8")
print(str2)
print(str1 == str2) # Returns True