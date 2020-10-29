import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileWorking {
	private File file1;
	private File file2;
	private File file3;
	private File file4;
	private File file5;
	private File file6;
	private File file7;
	private Java8 java8;

	public FileWorking(String fisier1, String fisier2, String fisier3, String fisier4, String fisier5, String fisier6,String fisier7,
			String cale) {
		java8 = new Java8(cale);
		file1 = new File(fisier1);
		file2 = new File(fisier2);
		file3 = new File(fisier3);
		file4 = new File(fisier4);
		file5 = new File(fisier5);
		file6 = new File(fisier6);
		file7=new File(fisier7);
		creare_fisiere();

	}

	private void creare_fisiere() {
		scriereListMonitoredData(java8.getListaProgram(), this.file1);
		scriereNrActivitati(java8.getNrZile(), this.file2);
		scriereMapStringLong(java8.getAparitiiActivitate(), this.file3, "Aparitii:");
		scriereMapStringMapStringLong(java8.getNrActipitatePeZi(), file4);
		scriereDurataFiecareActivitate(java8.getDurataFiecareActivitate(), file5);
		scriereListString(java8.getActivitatiDurataScurta(), file6);
		scriereMapStringLong(java8.getDurataTotalaActivitate(), file7, "Timp Total:");

	}

	private BufferedWriter createBufferedWriter(File file) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			return new BufferedWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void scriereListMonitoredData(List<MonitoredData> list, File file) {
		BufferedWriter writer = createBufferedWriter(file);
		list.forEach(x -> {
			try {
				writer.write(x.toString());
				writer.newLine();
				// writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void scriereListString(List<String> list, File file) {
		BufferedWriter writer = createBufferedWriter(file);
		list.forEach(x -> {
			try {
				writer.write(x);
				writer.newLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void scriereNrActivitati(Long nrActivitati, File file) {
		System.out.println(nrActivitati);
		BufferedWriter writer = createBufferedWriter(file);
		try {
			System.out.println("aici");
			writer.write(nrActivitati.toString());
			writer.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void scriereMapStringLong(Map<String, Long> map, File file, String msg) {
		BufferedWriter writer = createBufferedWriter(file);

		map.forEach((k, v) -> {
			try {
				writer.write(k.toString());

				writer.write(" ");
				writer.write(msg);
				writer.write(v.toString());
				writer.newLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void scriereMapStringMapStringLong(Map<Integer, Map<String, Long>> map, File file) {
		BufferedWriter writer = createBufferedWriter(file);

		map.forEach((k, v) -> {
			try {
				writer.write("Ziua:");
				writer.write(k.toString());
				writer.newLine();
				v.forEach((x, y) -> {
					try {
						writer.write("\n Activitate:");
						writer.write(x);
						writer.write(" Durata:");
						writer.write(y.toString());
						writer.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});
				writer.newLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void scriereDurataFiecareActivitate(Map<MonitoredData, Long> map, File file) {
		BufferedWriter writer = createBufferedWriter(file);

		map.forEach((k, v) -> {
			try {
				writer.write(k.toString());

				writer.write(" ");
				writer.write("Durata Minute:");
				writer.write(v.toString());
				writer.newLine();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
