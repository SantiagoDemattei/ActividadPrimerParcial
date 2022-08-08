package Dominio;

public interface Categoria {

    public void consultarVueloExistente(Usuario user) throws Exception;
    public String getNombre();
    public Integer getId();
    public Integer getCantMax();
    public void setId(Integer id);
    public void setNombre(String nombre);
    public void setCantMax(Integer cant);
}
