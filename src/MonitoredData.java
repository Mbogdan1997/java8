import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoredData {

	private String numeActivitate;
	private String timpIncepere;
	private String timpFinalizare;

	public MonitoredData(String numeActivitate, String timpIncepere, String timpFinalizare) {
		super();
		this.numeActivitate = numeActivitate;
		this.timpIncepere = timpIncepere;
		this.timpFinalizare = timpFinalizare;
	}

	public String getNumeActivitate() {
		return numeActivitate;
	}

	public void setNumeActivitate(String numeActivitate) {
		this.numeActivitate = numeActivitate;
	}

	public String getTimpIncepere() {
		return timpIncepere;
	}

	public void setTimpIncepere(String timpIncepere) {
		this.timpIncepere = timpIncepere;
	}

	public String getTimpFinalizare() {
		return timpFinalizare;
	}

	public void setTimpFinalizare(String timpFinalizare) {
		this.timpFinalizare = timpFinalizare;
	}

	public String toString() {
		String string = "Nume Activitate:" + this.numeActivitate + " Timp Incepere:" + this.timpIncepere
				+ " Timp Finalizare:" + this.timpFinalizare + "\n";
		return string;
	}

	public Date getDataTimpIncepere(String string) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(string);

			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	public int getData() {
		return this.getDataTimpIncepere(this.getTimpIncepere()).getDate();
	}

	public Long getTimpfinalSecunde() {
		return (this.getDataTimpIncepere(this.timpFinalizare).getTime()
				- this.getDataTimpIncepere(this.getTimpIncepere()).getTime()) / 1000;
	}

	public Long getTimpfinalMinute() {
		return this.getTimpfinalSecunde() / 60;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeActivitate == null) ? 0 : numeActivitate.hashCode());
		result = prime * result + ((timpFinalizare == null) ? 0 : timpFinalizare.hashCode());
		result = prime * result + ((timpIncepere == null) ? 0 : timpIncepere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonitoredData other = (MonitoredData) obj;
		if (numeActivitate == null) {
			if (other.numeActivitate != null)
				return false;
		} else if (!numeActivitate.equals(other.numeActivitate))
			return false;
		if (timpFinalizare == null) {
			if (other.timpFinalizare != null)
				return false;
		} else if (!timpFinalizare.equals(other.timpFinalizare))
			return false;
		if (timpIncepere == null) {
			if (other.timpIncepere != null)
				return false;
		} else if (!timpIncepere.equals(other.timpIncepere))
			return false;
		return true;
	}

}
