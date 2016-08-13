import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;

public class Disassembler {
	
	InstructionHandler inst;
	
	public Disassembler(){
			
		inst = new InstructionHandler();

	}
		

	public static void main(String[] args) {
		// 
		
		Disassembler ds = new Disassembler();
		
//		int temp = 0x8EF30018;
//		
//		long temp2 = Integer.toUnsignedLong(temp);
//		
//		
//		System.out.println(ds.inst.getStrOpcode((int) ds.inst.getUnsignedOp(temp2)));
		
		ArrayList<Integer> instruction = new ArrayList();
		instruction.add(0x022DA822);
		instruction.add(0x8EF30018);
		instruction.add(0x12A70004);
		instruction.add(0x02689820);
		instruction.add(0xAD930018);
		instruction.add(0x02697824);
		instruction.add(0xAD8FFFF4);
		instruction.add(0x018C6020);
		instruction.add(0x02A4A825);
		instruction.add(0x158FFFF6);
		instruction.add(0x8E59FFF0);
		
		int address = 0x7A060;
		
		for(int i=0; i<instruction.size(); i++)
		{
			int tempInst = instruction.get(i).intValue();
			int tempOpcode;
			int tempSrc1Reg;
			int tempSrc2Reg;
			int tempDestReg;
			String tempFunc;
			String tempOp;
			short tempOffset;
			short uncomp;
			if(ds.inst.getOpcode(tempInst) == 0)
			{
				tempSrc1Reg = ds.inst.getSrc1Reg(tempInst);
				tempSrc2Reg = ds.inst.getSrc2Reg(tempInst);
				tempDestReg = ds.inst.getDestReg(tempInst);
				tempFunc = ds.inst.getStrFunc(tempInst);
				System.out.println(Integer.toHexString(address) + "     "+ tempFunc + "  $" + tempDestReg + ", $"
						+ tempSrc1Reg + ", $"
						+ tempSrc2Reg);
				address += 0x4;
			}
			else if ((ds.inst.getOpcode(tempInst) == 4) || (ds.inst.getOpcode(tempInst) == 5))
			{
				tempOpcode = ds.inst.getOpcode(tempInst);
				tempSrc1Reg = ds.inst.getSrc1Reg(tempInst);
				tempDestReg = ds.inst.getSrc2Reg(tempInst);
				tempOffset = ds.inst.getOffset(tempInst);
				tempOp = ds.inst.getStrOpcode(tempInst);
				uncomp = ds.inst.uncompOffset(tempOffset);
				System.out.println(Integer.toHexString(address) + "     " + tempOp + "  $" 
						+ tempDestReg + ", $"
						+ tempSrc1Reg + ", "
						+ " address: "
						+ (Integer.toHexString(address + 4 + uncomp)));
				address += 0x4;
			}
			else
			{
				tempOpcode = ds.inst.getOpcode(tempInst);
				tempSrc1Reg = ds.inst.getSrc1Reg(tempInst);
				tempDestReg = ds.inst.getSrc2Reg(tempInst);
				tempOffset = ds.inst.getOffset(tempInst);
				long unsigned = Integer.toUnsignedLong(tempInst);
				tempOp = ds.inst.getUnsigned((int) ds.inst.getUnsignedOp(unsigned));
				System.out.println(Integer.toHexString(address) + "     " + tempOp + "  $" 
						+ tempDestReg + ", " + tempOffset + "($"
						+ tempSrc1Reg + ")");
				address +=0x4;
				
			}
			
		}
	}

}
