package oracle.net.ns;

public class MarkerPacket extends Packet
  implements SQLnetDef
{
  protected int type;
  protected int data;
  private boolean isReset;
  private boolean isBreak;

  public MarkerPacket(SessionAtts paramSessionAtts)
  {
    super(paramSessionAtts);
    createBuffer(10, 12, 0);
    this.buffer[4] = 12;
    this.buffer[8] = 0;
  }

  public MarkerPacket(SessionAtts paramSessionAtts, int paramInt)
  {
    super(paramSessionAtts);
    createBuffer(11, 12, 0);
    this.buffer[4] = 12;
    this.buffer[8] = 1;
    this.buffer[10] = (byte)paramInt;
  }

  public MarkerPacket(Packet paramPacket)
    throws NetException
  {
    super(paramPacket);
    this.type = this.buffer[8];
    switch (this.type)
    {
    case 0:
      this.data = 0;
      this.isBreak = true;
      break;
    case 1:
      this.data = this.buffer[10];
      if (this.data == 2)
        this.isReset = true;
      else
        this.isBreak = true;
      break;
    default:
      throw new NetException(205);
    }
  }

  public boolean isBreakPkt()
  {
    return this.isBreak;
  }
}

/* Location:           /Users/admin/.m2/repository/com/alibaba/external/jdbc.oracle/10.2.0.2/jdbc.oracle-10.2.0.2.jar
 * Qualified Name:     oracle.net.ns.MarkerPacket
 * JD-Core Version:    0.6.0
 */