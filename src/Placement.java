import java.text.DateFormat;
import java.util.UUID;

public class Placement implements Product{
    final private UUID id;
    private double balance;
    private DateFormat date;
    public Placement(){
        id = UUID.randomUUID();
    }
    public void manageOverdraft(){

    }
    public void countInterest(){

    }

    @Override
    public UUID getId() {
        return id;
    }
}
