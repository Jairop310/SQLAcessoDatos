package Encriptacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;


public class CrearEncriptacion {

	public static void main(String[] args) {
				Properties props = new Properties();
				try {
					props.load(new FileInputStream("db.properties"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String clave = props.getProperty("jdbc.password");
				
				
				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

				encryptor.setPassword("PASSWORD");

				String claveEncriptada = PropertyValueEncryptionUtils.encrypt(clave, encryptor);
				
			
				props.setProperty("jdbc.password", claveEncriptada);

				OutputStream os;
				try {
					os = new FileOutputStream("generado.properties");
					props.store(os, "Fichero generado automaticamente");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
	}

}
