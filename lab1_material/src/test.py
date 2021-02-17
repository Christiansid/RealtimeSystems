import threading
def tjena():
    while True:
        print("Tjena", end = '\n')

thread1 = threading.Thread(target = tjena())
thread1.start()