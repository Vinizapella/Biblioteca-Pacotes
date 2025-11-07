package com.biblioteca.Tech;

import com.biblioteca.Tech.service.EmprestimoService;
import com.biblioteca.Tech.service.LivroService;
import com.biblioteca.Tech.view.BibliotecaView;

public class Main {
    public static void main(String[] args) {
        EmprestimoService emprestimoService = new EmprestimoService();
        LivroService livroService = new LivroService(emprestimoService);
        BibliotecaView view = new BibliotecaView(livroService);
        view.mostrarMenu();
    }
}