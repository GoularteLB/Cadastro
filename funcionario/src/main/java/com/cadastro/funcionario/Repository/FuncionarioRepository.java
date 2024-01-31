package com.cadastro.funcionario.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.cadastro.funcionario.Config.Conexao;
import com.cadastro.funcionario.Model.Funcionario;

@Repository
public class FuncionarioRepository {
    public void salvar(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String consulta = "INSERT INTO FUNCIONARIO (ID, NOME, LOGIN , SENHA) VALUES(NEXTVAL('FUNCIONARIO_ID_SEQ'),?,?,?)";

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

    public ArrayList<Funcionario> lista() {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM funcionario";

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();

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

    public void excluir(int id) throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String consulta = "DELETE FROM FUNCIONARIO WHERE ID =?";

        PreparedStatement pstm;
        pstm = conn.prepareStatement(consulta);
        pstm.setInt(1, id);
        pstm.execute();
        try {

        } catch (Exception e) {
            System.out.println("Não conseguiu excluir a tabela funcionario");

        } finally {
            conexao.desconectar(conn);
        }

    }
}
