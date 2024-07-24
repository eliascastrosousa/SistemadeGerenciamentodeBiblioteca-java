package dev.eliascastro.sgb.infra.livro;

public enum CategoriaLivro {

    Ciencia_da_computaçao_informaçao_e_generalidades(000),
    Filosofia_e_psicologia(100),
    Religiao(200),
    Ciencias_sociais(300),
    Linguas(400),
    Ciencias_puras(500),
    Tecnologia_e_ciencias_aplicadas(600),
    Artes_e_recreaçao(700),
    Literatura(800),
    Historia_e_geografia(900);

    public int codigoCategoriaLivro;
    CategoriaLivro(int codigo) {
        codigoCategoriaLivro = codigo;
    }

}
