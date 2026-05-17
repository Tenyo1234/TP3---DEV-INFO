/**
 * Author : Thomas Labbe
 * Ordre de conception : 4e
 */

package logique;

public class ResultatExperience
{
    private boolean existe;
    private boolean success;

    public  ResultatExperience()
    {
        this.existe = false;
        this.success = false;
    }

    public boolean getExiste()
    {
        return existe;
    }
    public void setExiste(boolean existe)
    {
        this.existe = existe;
    }

    public boolean getSuccess()
    {
        return success;
    }
    public void setSuccess(boolean success)
    {
        this.success = success;
    }

}