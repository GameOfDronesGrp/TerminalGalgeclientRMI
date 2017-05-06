package logik;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import database.ScoreDTO;

/**
 *
 * @author mohammad
 */
public interface GalgeServiceI extends Remote{
    public String sayHello() throws RemoteException;
    public ArrayList<String> getBrugteBogstaver(String user, String pass) throws RemoteException;
    public String getSynligtOrd(String user, String pass) throws RemoteException;
    public String getOrdet(String user, String pass) throws RemoteException;
    public int getAntalForkerteBogstaver(String user, String pass) throws RemoteException;
    public boolean erSidsteBogstavKorrekt(String user, String pass)throws RemoteException;
    public boolean erSpilletVundet(String user, String pass) throws RemoteException;
    public boolean erSpilletTabt(String user, String pass) throws RemoteException;
    public boolean erSpilletSlut(String user, String pass) throws RemoteException;
    public void nulstil(String user, String pass) throws RemoteException;
    public void opdaterSynligtOrd(String user, String pass) throws RemoteException;
    public void gætBogstav(String user, String pass, String bogstav) throws RemoteException;
    public boolean hentBruger(String brugernavn, String password) throws RemoteException;
    public String getFornavn(String user, String pass) throws RemoteException;
    public List<ScoreDTO> getRankList(String user, String pass) throws RemoteException;
    public int getScore(String user, String pass) throws RemoteException;
}
