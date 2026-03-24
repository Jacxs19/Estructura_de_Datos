public class Persona {
    private String nombre;
    private int DNI;
    public Persona(String n, int dni){
        nombre=n;
        DNI=dni;
    }
    public void cambiarDni(int dni){
        DNI=dni;
    }
    public void cambiarNombre(String n){
        nombre=n;
    }
    public String toString(){
        return nombre+" "+DNI;
    }
}
