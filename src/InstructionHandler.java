import java.util.Dictionary;
import java.util.Hashtable;

public class InstructionHandler {
	   
	private Integer opcode;
	private Integer src1Reg;
	private Integer src2Reg;
	private Integer destReg;
	private Integer function;
	short offset;
	
	public int getSrc1Reg(int instruction){
		src1Reg = (instruction & 0x03E00000)>>>21;
		return src1Reg;
	}
	
	public int getSrc2Reg(int instruction){
		src2Reg = (instruction & 0x001F0000)>>>16;
		return src2Reg;
	}
	
	public int getDestReg(int instruction){
		destReg = (instruction & 0x0000F800)>>>11;
		return destReg;
	}
	
	public int getFunction(int instruction){
	    function = (instruction & 0x0000003F);
		return function;
	}
	
	public int getOpcode(int instruction){
		opcode = (instruction & 0xFC000000)>>>26;
		return opcode;
	}
	
	public long getUnsignedOp(long instruction){
		opcode = (int) ((instruction & 0xFC000000)>>>26);
		return opcode; 
	}
	
	public short getOffset(int instruction){
		offset = (short) (instruction & 0xFFFF);
		return offset;
	}
	
	public short uncompOffset(short anOffset){
		short temp = (short) (anOffset << 2);
		return temp;
	}
	
	public String getStrFunc(int instruction){
	
		int function = getFunction(instruction);
		
		Hashtable opsec = new Hashtable();
		opsec.put(32, "add");
		opsec.put(34, "sub");
		opsec.put(36, "and");
		opsec.put(37, "or");
		opsec.put(42, "slt");
		opsec.put(35, "lw");
		opsec.put(43, "sw");
		opsec.put(4, "beq");
		opsec.put(5, "bne");
		
		while(opsec.containsKey(function)){
			return opsec.get(function).toString();
		}
		return "Function not found";
	}
	
	public String getStrOpcode(int instruction){
		
		int operation = getOpcode(instruction);
		
		
		Hashtable opsec = new Hashtable();
		opsec.put(32, "add");
		opsec.put(34, "sub");
		opsec.put(36, "and");
		opsec.put(37, "or");
		opsec.put(42, "slt");
		opsec.put(35, "lw");
		opsec.put(43, "sw");
		opsec.put(4, "beq");
		opsec.put(5, "bne");
		
		while(opsec.containsKey(operation)){
			return opsec.get(operation).toString();
		}
		return "Operation not found";
		
	}
	
	public String getUnsigned(int instruction){
		int operation = instruction;
		
		Hashtable opsec = new Hashtable();
		opsec.put(32, "add");
		opsec.put(34, "sub");
		opsec.put(36, "and");
		opsec.put(37, "or");
		opsec.put(42, "slt");
		opsec.put(35, "lw");
		opsec.put(43, "sw");
		opsec.put(4, "beq");
		opsec.put(5, "bne");
		
		while(opsec.containsKey(operation)){
			return opsec.get(operation).toString();
		}
		return "operation not found";
	}

}
