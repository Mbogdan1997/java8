
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8 {

	private List<MonitoredData> listaProgram;
	private long nrZile;
	private Map<String, Long> aparitiiActivitate;
	private Map<Integer, Map<String, Long>> nrActipitatePeZi;
	private Map<MonitoredData, Long> durataFiecareActivitate;
	private Map<String, Long> durataTotalaActivitate;
	private List<String> activitatiDurataScurta;

	public Java8(String cale) {
		listaProgram = citire_fisier(cale);
		aparitiiActivitate = calculAparitiiActivitate();
		nrZile = numaraZile();
		durataFiecareActivitate = this.calculDurataFiecareActivitate();

		durataTotalaActivitate = this.calculDurataTotalaActivitate();
		activitatiDurataScurta = this.calculActivitatiScurte();

		nrActipitatePeZi = this.calculNrActivitatePeZi();

	}

	public static List<MonitoredData> citire_fisier(String cale) {

		try {
			return Files.lines(Paths.get(cale)).map((line) -> line.split("		")).map((x) -> {
				return new MonitoredData(x[2], x[0], x[1]);
			}).collect(Collectors.toList());
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	public Map<String, Long> calculAparitiiActivitate() {
		return this.listaProgram.stream()
				.collect(Collectors.groupingBy(MonitoredData::getNumeActivitate, Collectors.counting()));
	}

	public long numaraZile() {
		return listaProgram.stream().map(MonitoredData::getData).distinct().count();

	}

	public Map<Integer, Map<String, Long>> calculNrActivitatePeZi() {
		return listaProgram.stream().collect(Collectors.groupingBy(MonitoredData::getData,
				Collectors.groupingBy(MonitoredData::getNumeActivitate,Collectors.counting())));
	}

	public Map<MonitoredData, Long> calculDurataFiecareActivitate() {
		return listaProgram.stream().collect(
				Collectors.groupingBy(Function.identity(), Collectors.summingLong(MonitoredData::getTimpfinalMinute)));

	}

	public Map<String, Long> calculDurataTotalaActivitate() {
		return listaProgram.stream().collect(Collectors.groupingBy(MonitoredData::getNumeActivitate,
				Collectors.summingLong(MonitoredData::getTimpfinalMinute)));

	}

	public List<String> calculActivitatiScurte() {

		Map<String, Long> map = this.durataFiecareActivitate.keySet().stream()
				.filter((x) -> this.durataFiecareActivitate.get(x) < 5)
				.collect(Collectors.groupingBy(MonitoredData::getNumeActivitate, Collectors.counting()));
		map.forEach((k, v) -> System.out
				.println(k + "\n Nr Aparitii Total:" + this.aparitiiActivitate.get(k) + "\n Nr Aparitii Sub 5:" + v));
		return map.keySet().stream().filter((x) -> map.get(x) >= (float) 0.9 * (float) this.aparitiiActivitate.get(x))
				.collect(Collectors.toList());
	}

	public List<MonitoredData> getListaProgram() {
		return listaProgram;
	}

	public long getNrZile() {
		return nrZile;
	}

	public Map<String, Long> getAparitiiActivitate() {
		return aparitiiActivitate;
	}

	public Map<Integer, Map<String, Long>> getNrActipitatePeZi() {
		return nrActipitatePeZi;
	}

	public Map<MonitoredData, Long> getDurataFiecareActivitate() {
		return durataFiecareActivitate;
	}

	public Map<String, Long> getDurataTotalaActivitate() {
		return durataTotalaActivitate;
	}

	public List<String> getActivitatiDurataScurta() {
		return activitatiDurataScurta;
	}

}
