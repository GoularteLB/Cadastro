package com.funcionario.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.funcionario.demo.Model.funcionario;
import com.funcionario.demo.Repository.funcionarioRepository;

@Controller
public class funcionarioController {
    @Autowired
    funcionarioRepository repository;

    @GetMapping("/")
    public String index() {
        return "Cadastrofuncionario";
    }

    @GetMapping("/funcionario/cadastro")
    public String cadastroFuncionario() {
        return "Cadastrofuncionario";
    }

    @PostMapping("/funcionario/salvar")
    public String salvarFuncionario(funcionario funcionario) {
        System.out.println("funcionario");
        // repository.salvar(funcionario);
        // return "Cadastrofuncionario";
        return "redirect:/funcionario/lista";
    }

    @GetMapping("/funcionario/lista")
    public ModelAndView Consultar() {
        ModelAndView mv = new ModelAndView("ListaFuncionario");
        ArrayList<funcionario> funcionarios = new ArrayList<>();
        funcionarios = repository.lista();
        mv.addObject("funcionarios", funcionarios);
        return mv;
 
        
    }
}
