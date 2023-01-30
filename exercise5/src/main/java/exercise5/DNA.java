package exercise5;
import java.lang.Math;
import java.util.Set;
import java.util.HashSet;


class DNA{

    private String sequence;
    private int length;
    private double mass;
    private final String modifiedSeq;
    private final int modifiedLen;
    private final double MASS_A = 135.128;
    private final double MASS_C = 111.103;
    private final double MASS_G = 151.128;
    private final double MASS_T = 125.107;
    private final double MASS_JUNK = 100.0;

    DNA(String seq){
        modifiedSeq = modify(seq);
        this.modifiedLen = modifiedSeq.length();
        if(modifiedSeq.length()%3 == 0) {
            this.sequence = seq;
            this.length = seq.length();
        } else{
            throw new IllegalArgumentException("Invalid DNA sequence");
        }
    }

    /**
     * Assign modifiedSeq a modified sequence that only contains A, C, G, T characters
     */
    private String modify(String seq){
        String modifiedSequence = "";
        for(int i = 0; i < seq.length(); i++){
            if(isACGT(seq.charAt(i))){
                modifiedSequence += seq.charAt(i);
            }
        }
        return modifiedSequence;
    }


    /**
     * @return true if the sequence is a protein and false otherwise
     * Sequence is a protein if it satisfies these criteria:
     *     - Starts with the codon ATG
     *     - End with one of the codons TAA, TAG, TGA
     *     - Contains at least five codons
     *     - C and G account for at least 30% of the total mass
     */
    boolean isProtein(){
        return ((modifiedSeq.substring(0,3).equals("ATG"))
        && (modifiedSeq.substring(modifiedLen-3, modifiedLen).equals("TAA")
                || modifiedSeq.substring(modifiedLen-3, modifiedLen).equals("TAG")
                || modifiedSeq.substring(modifiedLen-3, modifiedLen).equals("TGA"))
        && (modifiedLen >= 15)
        && ((nucleotideCount('C') * MASS_C + nucleotideCount('G') * MASS_G)/mass > 0.3));
    }

    /**
     * @return a double value representing the total mass of the
     * DNA sequence rounded to one digit past the decimal point.
     */
    double totalMass(){
        mass = nucleotideCount('A') * MASS_A + nucleotideCount('C') * MASS_C
                + nucleotideCount('G') * MASS_G + nucleotideCount('T') * MASS_T
                + (length - modifiedLen) * MASS_JUNK;
        return Math.round(mass*10.0)/10.0;
    }

    /**
     * @return the count of the nucleotide in the DNA sequence
     * and 0 for all the invalid/junk nucleotides
     */
    int nucleotideCount(char c){
        int count = 0;
        if(isACGT(c)){
            for(int i = 0; i < modifiedLen; i++) {
                if (modifiedSeq.charAt(i) == c) {
                    count++;
                }
            }
            return count;
        } else{
            return 0;
        }
    }

    /**
     * @return a set that contains all the distinct codons in the DNA sequence
     */
    Set<String> codonSet(){
        Set<String> uniqueCodons = new HashSet<>();
        for(int i = 0; i < modifiedLen; i += 3){
            uniqueCodons.add(modifiedSeq.substring(i,i+3));
        }
        return uniqueCodons;
    }

    /**
     * Alters the DNA sequence by replacing all the occurences of the
     * originalCodon with newCodon, and eliminates all junk regions.
     */
    void mutateCodon(String originalCodon, String newCodon){
        if(newCodon.length() == 3 && originalCodon.length() == 3
        && newCodon.equals(modify(newCodon)) && originalCodon.equals(modify(originalCodon))){
            sequence = modifiedSeq.replace(originalCodon, newCodon);
            length = sequence.length();
        }
    }

    /**
     * @return the nucleotide sequence
     */
    String sequence(){
        return sequence;
    }

    /**
     * @return true if the input is one of A, C, G, T
     */
    private boolean isACGT(char c){
        return (c == 'A' || c == 'C' || c == 'G' || c == 'T');
    }
}