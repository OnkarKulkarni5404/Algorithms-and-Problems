P10=[3,5,2,7,4,10,1,9,8,6]
P8=[6,3,7,4,8,5,10,9]
Ip=[2,6,3,1,4,8,5,7]
Ip_Inverse=[4,1,3,5,7,2,8,6]
EP=[4,1,2,3,2,3,4,1]
S0=[[1,0,3,2],[3,2,1,0],[0,2,1,3],[3,1,3,2]]
S1=[[0,1,2,3],[2,0,1,3],[3,0,1,0],[2,1,0,3]]
P4=[2,4,3,1]

def permute(inputByte,permTable):
	return "".join([inputByte[i-1] for i in permTable])

def Shift(inputByte,offset):
	return inputByte[offset::]+inputByte[:offset:]

def GenerateKeys(key):
	p10=permute(key,P10)
	K1=permute(Shift(p10[:5],1)+Shift(p10[5:],1),P8)
	K2=permute(Shift(p10[:5],3)+Shift(p10[5:],3),P8)
	return K1,K2

def XOR(I1,I2):
	return "".join(['0' if I1[i]==I2[i] else '1' for i in range(0,len(I1))])


def F(inputByte,subkey):
	ep=permute(inputByte,EP)
	xor=XOR(ep,subkey)
	s0=S0[int(xor[0]+xor[1],2)][int(xor[2]+xor[3],2)]
	s1=S1[int(xor[4]+xor[5],2)][int(xor[6]+xor[7],2)]
	return permute("{0:02b}".format(s0)+"{0:02b}".format(s1),P4)


def fk(inputByte,subkey):
	# print("inputByte ={}".format(inputByte))
	L=inputByte[:4]
	R=inputByte[4:]
	xor=XOR(L,F(R,subkey))
	return xor+R

def SW(inputByte):
	return inputByte[4:]+inputByte[:4]

def encrypt(key,plaintext):
	IP=permute(plaintext,Ip)
	K1,K2=GenerateKeys(key)
	return permute(fk(SW(fk(IP,K1)),K2),Ip_Inverse)

def decrypt(key,ciphertext):
	# print("Decryption ************************************")
	IP=permute(ciphertext,Ip)
	K1,K2=GenerateKeys(key)
	fk2=fk(IP,K2)
	sw=SW(fk2)
	fk1=fk(sw,K1)
	decrypted=permute(fk1,Ip_Inverse)
	#print("IP = {}".format(IP))
	#print("fk(IP,K2) ={}".format(fk2))
	#print("SW() ={}".format(sw))
	#print("fk(sw,K1)= {}".format(fk1))
	#print("FP ={}".format(decrypted))
	return decrypted	


def main():
	plaintext="10101010"
	key="1010000010"
	ciphertext=encrypt(key,plaintext)
	decrypted=decrypt(key,ciphertext)
	print("Plaintext= {}".format(plaintext))
	print("Encrypted= {}".format(ciphertext))
	print("Decrypted= {}".format(decrypted))


if __name__=="__main__":
	main()
