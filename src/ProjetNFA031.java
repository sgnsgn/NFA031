
public class ProjetNFA031 {

	public static int validationSaisie(String message, int limiteInferieure, int limiteSuperieure) {
		int saisie = -1;
		boolean check = false;
		do {
			try {
				Terminal.ecrireString(message);
				saisie = Terminal.lireInt();
				if (saisie >= limiteInferieure && saisie <= limiteSuperieure) {
					check = true;
				} else
					Terminal.ecrireStringln("Le nombre doit etre compris entre " + limiteInferieure + " et "
							+ limiteSuperieure + ". Recommencez");
			} catch (TerminalException ex) {
				Terminal.ecrireStringln("Ce n'est pas un nombre. Recommencez");
			}
		} while (!check);
		return saisie;
	}

	public static void afficherListeChatons(String[] tab) {
		Terminal.ecrireStringln("-----------------------------------");
		for (int i = 0; i < tab.length; i++) {
			Terminal.ecrireStringln((i + 1) + " " + tab[i]);
		}
		Terminal.ecrireStringln("-----------------------------------");
	}

	public static void afficherChatonParNote(double[] note, String[] nom, int[] vote) {

		int[] classement = new int[note.length];
		double[] copieNote = new double[note.length];
		int indice = -1;
		for (int i = 0; i < note.length; i++) {
			copieNote[i] = note[i];
		}

		for (int i = 0; i < classement.length; i++) {
			double valeurMax = -1;
			for (int j = 0; j < copieNote.length; j++) {
				if (copieNote[j] > valeurMax) {
					valeurMax = copieNote[j];
					indice = j;
				}
			}
			copieNote[indice] = -1;
			classement[i] = indice;
		}

		for (int i = 0; i < classement.length; i++) {
			if (vote[classement[i]] == 0 || vote[classement[i]] == 1) {
				System.out.println(classement[i] + 1 + " " + nom[classement[i]] + " note : " + note[classement[i]]
						+ "     " + vote[classement[i]] + " vote");
			} else
				System.out.println(classement[i] + 1 + " " + nom[classement[i]] + " note : "
						+ note[classement[i]] / vote[classement[i]] + "     " + vote[classement[i]] + " votes");
		}
	}

	public static void afficherNotePourUnChaton(String[] liste, double[] note, int[] vote) {

		int saisie = validationSaisie("Entrez le numero du chaton : ", 1, 5);
		int index = saisie - 1;
		if (vote[index] == 0 || vote[index] == 1) {
			Terminal.ecrireStringln(saisie + " " + liste[index] + note[index] + " (" + vote[index] + " vote)");
		} else {
			Terminal.ecrireStringln(
					saisie + " " + liste[index] + note[index] / vote[index] + " (" + vote[index] + " votes)");
		}
		Terminal.ecrireStringln("");
	}

	public static void ajouterNotePourUnChaton(double[] tabNotes, int[] tabVotes) {

		int index = validationSaisie("Entrez le numero du chaton : ", 1, 5) - 1;
		int note = validationSaisie("Entrez le nombre d'etoiles (1 a 5) : ", 1, 5);
		tabNotes[index] += note;
		tabVotes[index] += 1;
		Terminal.ecrireStringln("");

	}

	public static void main(String[] args) {
		String[] nomsChatons = { "Mimi      ", "Cachou    ", "Irish     ", "Lyra      ", "Willy     " };
		double[] notesChatons = new double[nomsChatons.length];
		int[] nbVoteParChaton = new int[nomsChatons.length];
		boolean check = true;
		do {
			Terminal.ecrireStringln("1: Afficher les chatons dans l'ordre des numeros");
			Terminal.ecrireStringln("2: Afficher les chatons par note");
			Terminal.ecrireStringln("3: Afficher la note pour un chaton");
			Terminal.ecrireStringln("4: Ajouter une note pour un chaton");
			Terminal.ecrireStringln("5: Quitter le programme");
			int choix = validationSaisie("Votre choix : ", 1, 5);
			if (choix == 1) {
				afficherListeChatons(nomsChatons);
				check = true;
			}
			if (choix == 2) {

				afficherChatonParNote(notesChatons, nomsChatons, nbVoteParChaton);
				check = true;
			}
			if (choix == 3) {
				afficherListeChatons(nomsChatons);
				afficherNotePourUnChaton(nomsChatons, notesChatons, nbVoteParChaton);
				check = true;
			}
			if (choix == 4) {
				afficherListeChatons(nomsChatons);
				ajouterNotePourUnChaton(notesChatons, nbVoteParChaton);
				check = true;
			}
			if (choix == 5)
				check = false;
		} while (check);

		Terminal.ecrireString("Merci d'avoir participe, a bientot");
	}

}
