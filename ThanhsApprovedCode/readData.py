import convertapi
import os
import aes128
def read_and_convert_to_xml(path_file):
    """
        Chuyển đổi file đầu vào sang xml
        convertapi.api_secret : key xử dụng API convert
    """
    convertapi.api_secret = 'NxekHU0t3Y3tZGVP'
    result = convertapi.convert('xml', { 'File': path_file})
    result.file.save('x.xml')
    
def read_content_xml(xml_file):
    """
        Chuyển file xml sang hex
    """
    file = open(xml_file, encoding="utf-8")
    xml_content = file.read() 
    return xml_content


def text_to_bytes_list(text):
    """Convert string to list byte"""
    utf8_bytes = text.encode('utf-8')
    hex_string = utf8_bytes.hex()
    for i in range(32 -len(hex_string) % 32):
        hex_string += "0"
    byte_list = [int(hex_string[i:i+2],16)for i in range(0, len(hex_string), 2)]
    return byte_list


def bytes_list_to_text(int_list):
    """Convert list byte to string"""
    byte_data = bytes(int_list)
    utf8_string = byte_data.decode('utf-8') 
    return utf8_string

        
