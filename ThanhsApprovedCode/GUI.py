import tkinter as tk
import aes128
import readData
from tkinter import filedialog
import os
from RSA_main import RSA 
import convertapi


def readfile_clicked(text,filepath):
    """
        convert file đó sang XML và show XML content
    """
    readData.read_and_convert_to_xml(filepath)
    text.delete("1.0", tk.END) 
    text.insert("1.0",readData.read_content_xml('x.xml'))
    
    

def select_file(filename_label,filepath):
    """
        Chọn file, lấy đường dẫn và hiển thị tên file được chọn
    """
    filepath[0] = filedialog.askopenfilename()
    if filepath[0]:
        filename = os.path.basename(filepath[0])
        filename_label.config(text=filename)
    else:
        filename_label.config(text="")

        
        
def set_text_value(text, new_value):
    """
        set giá trị cho text box
    """
    text.delete("1.0", tk.END)  
    text.insert("1.0", new_value)

    
def encrypt_clicked(plain_text,cipher_text, AES_key, encrypted_key_box,private_key_box):
    """
        Mã Hoá Plain Text
    """
    input = plain_text.get("1.0", tk.END)
    input = input[:-1]
    input_bytes = readData.text_to_bytes_list(input)
    
    key = AES_key.get('1.0', tk.END)
    key = key[:-1]
    
    s = ""
    for i in range(int(len(input_bytes) / 16)):
        output_list = aes128.encrypt(input_bytes[i*16:(i + 1)*16], key)
        for i in output_list:
            s += str('{:02x}'.format(i))        
    set_text_value(cipher_text, s)
    
    """
        Mã Hoá AES Key
    """
    rsa = RSA(int(key, 16))
    rsa.generate_key()
    set_text_value(encrypted_key_box,hex(rsa.RSA_encrypt()))
    set_text_value(private_key_box, str(rsa.private_key[0]) + '\n' + str(rsa.private_key[1]))


def decrypt_clicked(plain_text,cipher_text, AES_key, encrypted_key_box, private_key_box):
    """
        Giải Mã Cipher Text
    """
    input = cipher_text.get("1.0", tk.END)
    input = input[:-1]
    input_bytes = [int(input[i: i + 2], 16) for i in range(0, len(input), 2)]
    
    encrypted_key = encrypted_key_box.get('1.0', tk.END)
    encrypted_key = encrypted_key[:-1]
    
    private_key_box_content = private_key_box.get("1.0", tk.END)
    private_key_box_content[:-1]
    private_key = [int(private_key_box_content[:private_key_box_content.find('\n')]), int(private_key_box_content[private_key_box_content.find('\n') + 1:])]
                      
    key = hex(RSA.RSA_decrypt(int(encrypted_key, 16), private_key))[2:]

    output = []
    for i in range(int(len(input_bytes) // 16)):
        decrypt_block = aes128.decrypt(input_bytes[i*16:(i + 1)*16], key)
        output.extend(decrypt_block)
    
    
    s = readData.bytes_list_to_text(output)
    s = s.rstrip('\0')
    set_text_value(plain_text, s)
    set_text_value(AES_key,key)
    
    with open("output.xml", "w", encoding="utf-8") as file:
        file.write(s)
    
    convertapi.api_secret = 'NxekHU0t3Y3tZGVP'
    result = convertapi.convert('docx', { 'File': 'output.xml'})
    result.file.save('output.docx')


def display():
    window = tk.Tk()
    window.geometry("1200x700")
    window.title("Bài Tập Lớn OF Thành")
    # Plain text
    text_encrypt_label = tk.Label(window, text="Plain text")
    text_encrypt_label.config(font=("Arial",12))
    text_encrypt_label.place(x=145,y=20)
    
    text_encrypt = tk.Text(height=20, width=30)
    text_encrypt.place(x=70,y=50)
    
    # AES key
    AES_Key_label = tk.Label(window, text="AES Key")
    AES_Key_label.config(font=("Arial",12))
    AES_Key_label.place(x=445,y=20)
    
    AES_Key = tk.Text(height=7, width=28)
    AES_Key.place(x=365,y=50)
    
    # Encrypted AES Key
    Encrypted_AES_Key_label = tk.Label(window, text="Ecrypted AES Key")
    Encrypted_AES_Key_label.config(font=("Arial",12))
    Encrypted_AES_Key_label.place(x=680,y=20)
    
    Encrypted_AES_Key = tk.Text(height=7, width=28)
    Encrypted_AES_Key.place(x=635,y=50)
    
    # Private key
    Private_Key_label = tk.Label(window, text="Private Key")
    Private_Key_label.config(font=("Arial",12))
    Private_Key_label.place(x=550,y=220)
    
    Private_Key = tk.Text(height=7, width=42)
    Private_Key.place(x=435,y=250)
    
    # Cipher text
    text_decrypt_label = tk.Label(window, text="Cipher text")
    text_decrypt_label.config(font=("Arial",12))
    text_decrypt_label.place(x=975,y=20)
    
    text_decrypt = tk.Text(height=20, width=30)
    text_decrypt.place(x=905,y=50)
    
    # button
    encrypt_button = tk.Button(window, text="Mã hoá", command=lambda: encrypt_clicked(text_encrypt,text_decrypt,AES_Key,Encrypted_AES_Key,Private_Key), width = 20, height=2, font=("Time new romans", 10))
    encrypt_button.place(x = 170, y = 450)
    
    decrypt_button = tk.Button(window, text="Giải mã", command=lambda: decrypt_clicked(text_encrypt,text_decrypt,AES_Key,Encrypted_AES_Key,Private_Key), width = 20, height=2, font=("Time new romans", 10))
    decrypt_button.place(x = 420, y = 450)
    
    filepath = [""]
    readfile_button = tk.Button(window, text="Đọc file", command=lambda: readfile_clicked(text_encrypt, filepath[0]), width = 20, height=2, font=("Time new romans", 10))
    readfile_button.place(x = 670, y = 450)
    
    select_file_button = tk.Button(window, text="Chọn tệp tin", command=lambda: select_file(filename_label,filepath),font=("Time new romans", 10),width = 20, height=2)
    select_file_button.place(x = 920, y = 450)
    
    filename_label = tk.Label(window, text="",font=("Times New Roman", 11))
    filename_label.place(x = 465, y = 550)
    
    
    window.mainloop()

    

display()

