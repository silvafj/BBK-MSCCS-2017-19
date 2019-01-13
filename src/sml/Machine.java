package sml;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 * @author ...
 */
public class Machine {
  // The labels in the SML program, in the order in which
  // they appear (are defined) in the program

  private Labels labels;

  // The SML program, consisting of prog.size() instructions, each
  // of class Instruction (or one of its subclasses)
  private ArrayList<Instruction> prog;

  // The registers of the SML machine
  private Registers registers;

  // The program counter; it contains the index (in prog) of
  // the next instruction to be executed.

  private int pc;

  {
    labels = new Labels();
    prog = new ArrayList<>();
    pc = 0;
  }

  /**
   * Initialises the system and executes the program.
   *
   * @param args name of the file containing the program text.
   */
  public static void main(String[] args) {

    if (args.length != 1) {
      System.err.println("Incorrect number of arguments - Machine <file> - required");
      System.exit(-1);
    }

    Machine m = new Machine();
    Translator t = new Translator(args[0]);
    t.readAndTranslate(m.getLabels(), m.getProg());

    System.out.println("Here is the program; it has " + m.getProg().size() + " instructions.");
    System.out.println(m);

    System.out.println("Beginning program execution.");
    m.execute();
    System.out.println("Ending program execution.");

    System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");
  }

  /**
   * String representation of the program under execution.
   *
   * @return pretty formatted version of the code.
   */
  @Override
  public String toString() {
    StringBuffer s = new StringBuffer();
    for (int i = 0; i != getProg().size(); i++)
      s.append(getProg().get(i) + "\n");
    return s.toString();
  }

  /**
   * Execute the program in prog, beginning at instruction 0.
   * Precondition: the program and its labels have been store properly.
   */
  public void execute() {
    setPc(0);
    setRegisters(new Registers());
    while (getPc() < getProg().size()) {
      Instruction ins = getProg().get(getPc());
      setPc(getPc() + 1);
      ins.execute(this);
    }
  }

  public Labels getLabels() {
    return this.labels;
  }

  public void setLabels(Labels labels) {
    this.labels = labels;
  }

  public ArrayList<Instruction> getProg() {
    return this.prog;
  }

  public void setProg(ArrayList<Instruction> prog) {
    this.prog = prog;
  }

  public Registers getRegisters() {
    return this.registers;
  }

  public void setRegisters(Registers registers) {
    this.registers = registers;
  }

  public int getPc() {
    return this.pc;
  }

  public void setPc(int pc) {
    this.pc = pc;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Machine)) return false;
    final Machine other = (Machine) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$labels = this.labels;
    final Object other$labels = other.labels;
    if (!Objects.equals(this$labels, other$labels)) return false;
    final Object this$prog = this.prog;
    final Object other$prog = other.prog;
    if (!Objects.equals(this$prog, other$prog)) return false;
    final Object this$registers = this.registers;
    final Object other$registers = other.registers;
    if (!Objects.equals(this$registers, other$registers)) return false;
    if (this.pc != other.pc) return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $labels = this.labels;
    result = result * PRIME + ($labels == null ? 0 : $labels.hashCode());
    final Object $prog = this.prog;
    result = result * PRIME + ($prog == null ? 0 : $prog.hashCode());
    final Object $registers = this.registers;
    result = result * PRIME + ($registers == null ? 0 : $registers.hashCode());
    result = result * PRIME + this.pc;
    return result;
  }

  protected boolean canEqual(Object other) {
    return other instanceof Machine;
  }
}
