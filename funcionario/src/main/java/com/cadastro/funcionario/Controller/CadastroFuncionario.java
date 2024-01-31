package com.cadastro.funcionario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.sql.SQLException;
import com.cadastro.funcionario.Model.Funcionario;
import com.cadastro.funcionario.Repository.FuncionarioRepository;

@Controller
public class CadastroFuncionario {
    @Autowired
    FuncionarioRepository repository;

    @GetMapping("/cadastro")
    public String Cadastra() {
        return "cadastroFuncionario";
    }

    @GetMapping("/cadastro/funcionario")
    public String CadastraFuncionario() {
        return "cadastroFuncionario";
    }

    @GetMapping("/lista/funcionario")
    public ModelAndView consulta() {
        ModelAndView mv = new ModelAndView("listaFuncionario");
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = repository.lista();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @PostMapping("/funcionario/salvar")
    public String Salvar(Funcionario funcionario) {
        repository.salvar(funcionario);
        return "redirect:/lista/funcionario";
    }

    @PostMapping("/funcionario")
    public String NovoFuncionario(Funcionario funcionario) {
        repository.salvar(funcionario);
        return "redirect:/lista/funcionario";
    }

    @GetMapping("/funcionario/excluir{id}")
    public String Excluir(@PathVariable("id") int id) throws SQLException {
        repository.excluir(id);
        return "redirect:/lista/funcionario";

    }
}
