@RunnerAnnotation
public class Runner {

    @RunnerAnnotation(name = "Troquei de nome", age = 22)
    public String teste;

    @RunnerAnnotation
    public int oi;

    public void foiComSucesso(){
        System.out.println(this.teste);
    }
}
