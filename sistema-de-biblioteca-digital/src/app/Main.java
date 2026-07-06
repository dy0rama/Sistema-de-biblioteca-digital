package app;

import services.ArquivoService;
import services.BibliotecaService;
import util.Menu;

public class Main {
    static void main() {
        BibliotecaService biblioteca = new BibliotecaService();
        ArquivoService arquivo = new ArquivoService();

        Menu menu = new Menu(biblioteca, arquivo);

        menu.iniciar();
    }
}
