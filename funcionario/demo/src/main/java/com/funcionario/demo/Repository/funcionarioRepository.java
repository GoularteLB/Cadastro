package com.funcionario.demo.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.funcionario.demo.Config.conexao;
import com.funcionario.demo.Model.funcionario;

@Repository
public class funcionarioRepository {
    public void salvar(funcionario funcionario) {
        conexao conexao = new conexao();
        Connection conn = conexao.conectar();
        try {
            String consulta = "INSERT INTO funcionario(ID, NOME, LOGIN, SENHA)" +
                    "VALUES(NEXTVAL('ALUNO_SEQ'),?,?,?)";
            PreparedStatement pstm;
            pstm = conn.prepareStatement(consulta);

            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getLogin());
            pstm.setString(3, funcionario.getSenha());

            pstm.execute();
        } catch (Exception e) {

        } finally {
            conexao.desconectar(conn);
        }
    }

    public ArrayList<funcionario> lista() {
        conexao conexao = new conexao();
        Connection conn = conexao.conectar();
        ArrayList<funcionario> funcionarios = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM funcionario";

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                funcionario funcionario = new funcionario();

                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionarios.add(funcionario);
            }
        } catch (Exception ex) {

        } finally {
            conexao.desconectar(conn);
        }
        return funcionarios;
    }
}
