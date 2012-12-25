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
 * �����������Ķ���Э�������
 * ��ͨѶЭ��

��ǩ���ݸ�ʽ���£�ASCII�룩��

�� ����ͷ��    0xFFFF,��ʾ���Ŀ�ʼ��

�� �������ͣ�    ʮ���������ݣ�1���ֽڣ��̶�Ϊ0x01��ָʾ�ñ���Ϊ��ǩ���ݣ�

�� ��ͷID��    ʮ���������ݣ�2���ֽڣ�

�� ��ǩID��    ʮ�����ƣ�5���ֽڣ�ǰ4���ֽ�Ϊ��ǩ��ID�ţ�����ID������ֽڵ�BIT7-BIT4Ϊ��ɢ��Կ�ţ������һ���ֽ�Ϊ��ǩ��״̬�ֽڣ����б�ǩ״̬�ֽڵ���߱���λָʾ��ǩ�����Ƿ���㣨1����ǩ�������㣻0:��ǩ�������㣩����������λ������

�� ������ID��    ʮ���������ݣ�1���ֽڣ������������࣬�̶�Ϊ�� 1���ʾУ�ڣ� 2���ʾУ�⣻BIT7~BIT4��ʾ��ǩ���ȱ������Ĵ��������BIT3~BIT0��ʾ��󱻴����Ĵ����������0x12,��ʾ��ǩ�ȱ�1�ഥ����������Ȼ��2�ഥ��������������У����֮��0x21��ʾ��У��

�� RSSIֵ��    ʮ���������ݣ� 1���ֽڣ�BIT7~BIT4λ����ʾ�������Ĵ�����������ΧΪ0~15����λ�����㴥������ʱӦ���ô�����1��BIT3λΪ����λ��BIT2~BIT0ΪRSSI�ֽڣ�Ŀǰ������

�� CRC16У�飺ʮ���������ݣ� 2���ֽڣ�CRC16У��Ϊǰ�������ֽڵ�CRC16��У������������������ͷ0xFFFF����16 λ CRC У��Ķ���ʽ�� X16+X12+X5+1��
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
		// TODO: �Ķ��������Ŀ��ŵ�һλ��ʾ��Ƭ�ĵ�ص��� ��0Ϊ����1Ϊ��ص�����
		// ��ʵ�ּ�ؿ�Ƭ�����Ĺ���
		List<String> list = new ArrayList<String>();
		String s = "";
		String tigger = "";
		String rfid = "";
		try {
			for (int i = 1; i < str.length; i++) {
				s = str[i];
				// ��һλ��Ϊ01�� ȥ������ǩ�ź�01��ͷ����ͷ������02��ͷ������Щ������� ���Ȳ����� ȫ������
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
