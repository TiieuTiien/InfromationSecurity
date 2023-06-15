import random


def extended_euclidean_algorithm(a, b):
    a0, a1 = 1, 0
    b0, b1 = 0, 1
    tmp = b
    while b != 0:
        q = a // b
        a, b = b, a % b
        a_next = a0 - q * a1
        a0, a1 = a1, a_next
        b_next = b0 - q * b1
        b0, b1 = b1, b_next

    if(a0 < 0):
        a0 += tmp
    bezout_coefficients = [a0, b0]
    return bezout_coefficients

# --------------------------------------------------------------------------------------------------------

def pow(a,d,mod):
    result = 1
    while d > 0:
        if d % 2 == 1:
            result *= a
            result %= mod
        a *= a
        a %=mod
        d //= 2
    return result

# --------------------------------------------------------------------------------------------------------

def isPrime(n, k):
    if n < 2: return False
    if n < 4: return True
    if n % 2 == 0: return False    # speedup

    s = 0
    d = n-1
    while d % 2 == 0:
        s += 1
        d //= 2
    
    for i in range(k):
        a = random.randrange(2, n-1)
        x = pow(a,d,n)
        if x == 1: continue
        for j in range(s):
            if x == n-1: break
            x = (x * x) % n
        else:
            return False
    
    return True

# --------------------------------------------------------------------------------------------------------

def generate_prime_number(bit):
    while True:
        num = random.randint(2**(bit - 1) + 1, 2 ** bit)
        if isPrime(num,100):
            return num
        
# --------------------------------------------------------------------------------------------------------
        
class RSA:
    def __init__(self, plain_text):
        self.p = generate_prime_number(450)
        self.q = generate_prime_number(450)
        self.plain_text = plain_text    
        self.private_key = -1
        self.public_key = -1
    
    def generate_key(self):
        phi = (self.p - 1) * (self.q - 1)
        e = phi - 1
        self.public_key = [e, self.p * self.q]
        self.private_key = [extended_euclidean_algorithm(e, phi)[0], self.p * self.q]

    def RSA_encrypt(self):
        return pow(self.plain_text, self.public_key[0], self.public_key[1])
    
    def RSA_decrypt(self, cipher_text, private_key):
        return pow(cipher_text, private_key[0], private_key[1])
    
    def RSA_decrypt(cipher_text, private_key):
        return pow(cipher_text, private_key[0], private_key[1])
# a = RSA(57811460909138771071931939740208549692)
# a.generate_key()
# print(a.p, a.q)
# print(a.RSA_decrypt(a.RSA_encrypt(), a.private_key))
