def isPrime(n):
    if n < 2:
        return False

    for i in range(2, n):
        if n % i == 0:
            return False
    return True

def fact(n):
    num = 1
    for i in range(1,n+1):
        num = num * i
    return num

def choose(n,k):
    return fact(n)/(fact(k)*fact(n-k))

def numPrimes(n):
    num = 0
    for i in range(1, n+1):
        if isPrime(i):
            num = num + 1
    return num

def redJohn(n):
    sum = 0
    for i in range(0, n/4 + 1):
        sum = sum + fact(n-4*i+i)/(fact(i)*fact(n-4*i))
    return numPrimes(sum)

def numRedJohn(n):
    for i in range(1, n+1):
        print redJohn(int(raw_input()))


numRedJohn(int(raw_input()))
