package com.zephyr.studentsafe.mina.filter;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


/**
 * @author wanghongliang
 * 
 * 带触发器的阅读器协议解码器
 * ．通讯协议

标签数据格式如下（ASCII码）：

① 报文头：    0xFFFF,表示报文开始；

② 数据类型：    十六进制数据，1个字节，固定为0x01，指示该报文为标签数据；

③ 读头ID：    十六进制数据，2个字节；

④ 标签ID：    十六进制，5个字节：前4个字节为标签的ID号（其中ID号最高字节的BIT7-BIT4为分散密钥号），最后一个字节为标签的状态字节，其中标签状态字节的最高比特位指示标签电量是否充足（1：标签电量不足；0:标签电量充足），其他比特位保留；

⑤ 触发器ID：    十六进制数据，1个字节，触发器共两类，固定为： 1类表示校内， 2类表示校外；BIT7~BIT4表示标签最先被触发的触发器类别，BIT3~BIT0表示最后被触发的触发器类别。如0x12,表示标签先被1类触发器触发，然后被2类触发器触发，即出校。反之，0x21表示入校。

⑥ RSSI值：    十六进制数据， 1个字节；BIT7~BIT4位，表示触发器的触发次数，范围为0~15，上位机计算触发次数时应将该次数加1；BIT3位为保留位；BIT2~BIT0为RSSI字节，目前保留；

⑦ CRC16校验：十六进制数据， 2个字节；CRC16校验为前面所有字节的CRC16的校验运算结果（包括报文头0xFFFF）；16 位 CRC 校验的多项式是 X16+X12+X5+1；
 *
 */
public class RfidWithTriggerProtocolDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession arg0, IoBuffer arg1,
			ProtocolDecoderOutput arg2) throws Exception {
		byte[] readBuffer = null ;
		List list = new ArrayList();
		StringBuffer stringBuffer = null;
		while(arg1.hasRemaining())
		{
			readBuffer = new byte[arg1.limit()];
			stringBuffer = new StringBuffer();
			arg1.get(readBuffer) ;
			list = null;
			for (int i = 0 ; i < readBuffer.length; i++){
				int v = readBuffer[i] & 0xff ;
				String hv = Integer.toHexString(v);
				if (hv.length() <2 ){
					stringBuffer.append(0);
				}
				stringBuffer.append(hv);
			}
		
			list = getHexString(stringBuffer.toString().split("ffff"),((InetSocketAddress)arg0.getRemoteAddress()).getAddress().getHostAddress()) ;
			arg2.write(list);
			
		}
		return true; 
	}
	
	public static List<String> getHexString(String[] str,String ip) {
		// TODO: 阅读器传来的卡号第一位表示卡片的电池电量 。0为正常1为电池电量低
		// 需实现监控卡片电量的功能
		List<String> list = new ArrayList<String>();
		String s = "";
		String tigger = "";
		String rfid = "";
		try {
			for (int i = 1; i < str.length; i++) {
				s = str[i];
				// 第一位不为01的 去掉。标签信号01开头，读头心跳包02开头，还有些有乱码的 长度不够的 全部屏蔽
				if (!s.substring(0, 2).equals("01")) {
					continue;
				}
				rfid = s.substring(6, 14);
				tigger = s.substring(16, 18);
				list.add(Integer.valueOf(rfid, 16).toString() + "&" + tigger + "&" + ip);
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(s);
		}
		return list;
	}

		
}
