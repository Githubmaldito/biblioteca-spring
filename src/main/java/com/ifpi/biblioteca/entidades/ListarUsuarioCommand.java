package com.ifpi.biblioteca.entidades;

import com.ifpi.biblioteca.DAO.UserDB;

public class ListarUsuarioCommand {

public class ListarUsuariosCommand implements Command {
    private UserDB userDB; // Receiver

    public ListarUsuariosCommand(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public void execute() {
        userDB.listarUsuarios(); // Executa a operação no banco de dados
    }
}
}
