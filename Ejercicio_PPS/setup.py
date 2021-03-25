# -*- coding: utf-8 -*-
import os
from colorama import Fore

ROJO = Fore.RED
CIAN = Fore.CYAN
VERDE = Fore.GREEN

lampp_instalado = False
java_11_instalado = False

def imprime_bonito(cadena):
    print(f"{CIAN}=============================================================================================")
    print(f"{ROJO}\t {cadena}")
    print(f"{CIAN}=============================================================================================")

def pedirCadena(enunciado):
    imprime_bonito(enunciado)
    return input(f"{VERDE} ==> ")

def gestionar_lampp(opcion):
    imprime_bonito("SE REQUIEREN PRIVILEGIOS DE ADMINISTRADOR PARA LLEVAR A CABO ESTA ACCIÓN")
    if opcion[0].upper() == "I":
        opcion = "start"
    elif opcion[0].upper() == "R":
        opcion = "restart"
    elif opcion[0].upper() == "P":
        opcion = "stop"
    else:
        opcion = "status"
    os.system(f"sudo bash /opt/lampp/lampp {opcion}")

def comprobar_lampp():
    if os.path.isdir("/opt/lampp/"):
        imprime_bonito("LAMPP INSTALADO")
        gestionar_lampp("e")
        lampp_instalado = True
    else:
        instalar = pedirCadena("LAMPP NO ESTA INSTALADO, DESEA INSTALARLO AUTOMÁTICAMENTE. [S/n]")
        if instalar[0].upper() != "N":
            imprime_bonito("DESCANGANDO LAMPP DESDE SU SITIO OFICIAL...")
            os.system("wget https://www.apachefriends.org/xampp-files/7.3.27/xampp-linux-x64-7.3.27-1-installer.run")
            os.system("sudo chmod +x xampp-linux-x64-7.3.27-1-installer.run")
            imprime_bonito("LANZANDO INSTALADOR DE LAMPP")
            os.system("sudo ./xampp-linux-x64-7.3.27-1-installer.run")
            imprime_bonito("LAMPP INSTALADO")
            lampp_instalado = True
            gestionar_lampp("e")
        else:
            imprime_bonito("[!] SALIENDO. EL SCRIPT NO PUEDE CONTINUAR")
            exit()

def comprobar_java():
    if os.path.isdir("/etc/java-11-openjdk/"):
        imprime_bonito("JAVA 11 INSTALADO , SE PUEDE CONTINUAR")
        java_11_instalado = True
    else:
        instalar = pedirCadena("JAVA 11 NO ESTA INSTALADO, DESEA INSTALARLO AUTOMÁTICAMENTE. [S/n]")
        if instalar[0].upper() != "N":
            imprime_bonito("INSTALANDO JAVA 11")
            os.system("sudo apt install openjdk-11-jdk")
            imprime_bonito("JAVA 11 INSTALADO.")
            java_11_instalado = True
        else:
            imprime_bonito("[!] SALIENDO. EL SCRIPT NO PUEDE CONTINUAR")
            exit()

def comprobar_dependencias():
    comprobar_lampp()
    comprobar_java()
    correcto = (lampp_instalado and java_11_instalado)
    return correcto

def main():
    comprobar_dependencias()
    imprime_bonito("LANZANDO LAMPP")
    gestionar_lampp("I")
    imprime_bonito("LANZANDO APLICACION")
    pedirCadena("CUANDO CIERRE LA APLICACIÓN SE DETENDRÁN LOS SERVICIOS.\nPulse Intro para continuar.")
    os.system("java -jar EjercicioPPS.jar")
    gestionar_lampp("P")

if __name__ == '__main__':
    main()

