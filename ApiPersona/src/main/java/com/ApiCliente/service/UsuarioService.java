package com.ApiCliente.service;

import com.ApiCliente.models.Usuario;
import com.ApiCliente.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class  UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<Usuario>obtenerUsuarios(){
       return(ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


}
