/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.mapgen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import jp.l1j.mapgen.util.StreamUtils;
import jp.l1j.mapgen.util.StringUtils;

public class MapIdsBuilder extends MapInfoBuilder {

	final HashMap<Integer, String> _name = new HashMap<Integer, String>() {
		{
			FileReader fr = null;
			BufferedReader br = null;
			try {
				Config.load();
				fr = new FileReader(Config.ZONE_FILE);
				br = new BufferedReader(fr);
				String line = null;
				
				while ((line = br.readLine()) != null) {
					String[] token = line.split(",");
					put(Integer.valueOf(token[0]), token[1]);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} finally {
				StreamUtils.close(br, fr);
			}
		}
	};
	
	ArrayList<String> _infos = new ArrayList<String>();

	@Override
	public void add(int no, int xStart, int xEnd, int yStart, int yEnd) {
		String s = StringUtils.join(
						new Object[] {
							no,
							getName(no),
							xStart,
							xEnd,
							yStart,
							yEnd,
							getMonsterAmount(no),
							getDropRate(no),
							getUniqueRate(no),
							getUnderwater(no),
							getMakable(no),
							getTeleportable(no),
							getEscapable(no),
							getResurrection(no),
							getPainWand(no),
							getPenalty(no),
							getTakePets(no),
							getRecallPets(no),
							getUsableItem(no),
							getUsableSkill(no)
						}, ",");
		_infos.add(s);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("id,name,start_x,end_x,start_y,end_y,monster_amount,"
						+ "drop_rate,unique_rate,underwater,makable,teleportable,"
						+ "escapable,resurrection,painwand,penalty,take_pets,recall_pets,"
						+ "usable_item,usable_skill\r\n");
		result.append(StringUtils.join(_infos.toArray(), "\r\n"));
		return result.toString();
	}
	
	private String getName(int id) {
		String result = _name.get(id);
		if(result == null) {
			result = "\\N";
		}
		return result;
	}
	private int getMonsterAmount(int id) {
		switch(id) {
			default:
				return 1;
		}
	}
	
	private int getDropRate(int id) {
		switch(id) {
			default:
				return 1;
		}
	}
	
	private int getUniqueRate(int id) {
		switch(id) {
			default:
				return 1;
		}
	}
	
	private int getUnderwater(int id) {
		switch(id) {
			case   63:
			case   65:
			case  253:
			case  420:
			case  552:
			case  555:
			case  557:
			case  558:
			case  604:
			case  779:
			case 1011:
			case 1012:
			case 1013:
			case 1014:
			case 1015:
			case 1016:
				return 1;
			default:
				return 0;
		}
	}
	
	private int getMakable(int id) {
		switch(id) {
			case    0:
			case    4:
			case  304:
			case 1000:
			case 1001:
			case 6321:
				return 1;
			default:
				return 0;
		}
	}
	
	private int getTeleportable(int id) {
		switch(id) {
			case    0:
			case    1:
			case    3:
			case    4:
			case    7:
			case    8:
			case    9:
			case   10:
			case   11:
			case   12:
			case   13:
			case   14:
			case   15:
			case   16:
			case   17:
			case   18:
			case   19:
			case   20:
			case   22:
			case   23:
			case   24:
			case   25:
			case   26:
			case   27:
			case   28:
			case   29:
			case   30:
			case   31:
			case   32:
			case   33:
			case   34:
			case   35:
			case   36:
			case   43:
			case   44:
			case   45:
			case   46:
			case   47:
			case   48:
			case   49:
			case   50:
			case   51:
			case   52:
			case   53:
			case   54:
			case   55:
			case   56:
			case   57:
			case   58:
			case   59:
			case   60:
			case   61:
			case   62:
			case   63:
			case   64:
			case   68:
			case   69:
			case   72:
			case   73:
			case   74:
			case   75:
			case   76:
			case   77:
			case   85:
			case   86:
			case  205:
			case  206:
			case  207:
			case  208:
			case  300:
			case  301:
			case  304:
			case  305:
			case  307:
			case  308:
			case  309:
			case  330:
			case  420:
			case  440:
			case  441:
			case  480:
			case  482:
			case  483:
			case  484:
			case  502:
			case  507:
			case  522:
			case  523:
			case  524:
			case  541:
			case  542:
			case  543:
			case  558:
			case  600:
			case  601:
			case  602:
			case  603:
			case  604:
			case  605:
			case  606:
			case  607:
			case  608:
			case  610:
			case  611:
			case  613:
			case  620:
			case  621:
			case  666:
			case  702:
			case  703:
			case  704:
			case  705:
			case  706:
			case  707:
			case  708:
			case  709:
			case  710:
			case  711:
			case  712:
			case  713:
			case  714:
			case  715:
			case  716:
			case  717:
			case  718:
			case  719:
			case  720:
			case  721:
			case  722:
			case  723:
			case  724:
			case  777:
			case  778:
			case  779:
			case  780:
			case  783:
			case  784:
			case 1000:
			case 1001:
			case 2005:
			case 5166:
			case 5167:
			case 5300:
			case 5301:
			case 5302:
			case 5801:
			case 5802:
			case 5803:
			case 5804:
			case 5805:
			case 6041:
			case 6301:
			case 6302:
			case 6303:
			case 6311:
			case 6312:
			case 6313:
			case 6321:
			case 6322:
			case 6323:
			case 7000:
			case 7001:
			case 7002:
			case 7003:
			case 7004:
			case 7005:
			case 7006:
			case 7007:
			case 7008:
			case 7009:
			case 7010:
			case 7011:
			case 7012:
			case 7013:
			case 7014:
			case 7015:
			case 7016:
			case 7017:
			case 7018:
			case 7051:
			case 7052:
			case 7053:
			case 7054:
			case 7055:
			case 7057:
			case 7060:
			case 7061:
			case 7062:
			case 7063:
			case 7064:
			case 7065:
			case 7066:
			case 7067:
			case 7068:
			case 7069:
			case 7070:
			case 7071:
			case 7072:
			case 7073:
			case 7074:
			case 7075:
			case 7076:
			case 7077:
			case 7078:
			case 7079:
			case 7080:
			case 7100:
			case 7101:
			case 7102:
			case 7103:
			case 7104:
			case 7105:
			case 7106:
			case 7107:
			case 7108:
			case 7109:
			case 8104:
			case 8105:
			case 8106:
			case 8116:
			case 8117:
			case 8118:
			case 9100:
			case 9990:
			case 9991:
			case 9992:
			case 9993:
			case 9994:
			case 9995:
			case 9996:
			case 9997:
			case 9998:
			case 9999:
				return 1;
			default:
				return 0;
		}
	}
	
	private int getEscapable(int id) {
		switch(id) {
			case    34:
			case    37:
			case    38:
			case    39:
			case    40:
			case    41:
			case    42:
			case    65:
			case    67:
			case    70:
			case    90:
			case    99:
			case   200:
			case   303:
			case   666:
			case   997:
			case   998:
			case  4301:
			case  4831:
			case  5124:
			case  5143:
			case  5153:
			case  5302:
			case  6051:
			case  6801:
			case  6901:
			case  7058:
			case  7059:
			case  9000:
			case  9991:
			case  9992:
			case  9993:
			case  9994:
			case  9995:
			case  9997:
			case  9998:
			case  9999:
			case 17920:
				return 0;
			default:
				return 1;
		}
	}

	private int getResurrection(int id) {
		switch(id) {
			case    88:
			case    89:
			case    90:
			case    91:
			case    92:
			case    93:
			case    94:
			case    95:
			case    96:
			case    97:
			case    98:
			case   303:
			case   621:
			case   997:
			case   998:
			case  2005:
			case  4301:
			case  4831:
			case  5143:
			case  5153:
			case  5300:
			case  5301:
			case  5302:
			case  6051:
			case  6801:
			case  6901:
			case  9000:
			case 17920:
				return 0;
			default:
				return 1;
		}
	}

	private int getPainWand(int id){
		switch(id) {
			case     5:
			case     6:
			case    38:
			case    39:
			case    40:
			case    41:
			case    42:
			case    57:
			case    58:
			case    83:
			case    84:
			case    85:
			case    86:
			case    99:
			case   100:
			case   205:
			case   206:
			case   207:
			case   208:
			case   340:
			case   350:
			case   360:
			case   370:
			case   446:
			case   447:
			case   518:
			case   613:
			case   620:
			case   621:
			case   630:
			case   631:
			case   632:
			case   702:
			case   703:
			case   704:
			case   705:
			case   706:
			case   707:
			case   708:
			case   709:
			case   710:
			case   711:
			case   712:
			case   713:
			case   714:
			case   715:
			case   716:
			case   717:
			case   718:
			case   719:
			case   720:
			case   721:
			case   722:
			case   723:
			case   724:
			case   725:
			case   726:
			case   783:
			case   784:
			case   997:
			case   998:
			case  1002:
			case  1003:
			case  1004:
			case  1005:
			case  1006:
			case  1007:
			case  1008:
			case  1009:
			case  1010:
			case  1011:
			case  1012:
			case  1013:
			case  1014:
			case  1015:
			case  1016:
			case  1017:
			case  1018:
			case  1019:
			case  1020:
			case  1021:
			case  1022:
			case  1023:
			case  1024:
			case  1025:
			case  1026:
			case  1027:
			case  1028:
			case  2005:
			case  2006:
			case  4301:
			case  4831:
			case  5124:
			case  5143:
			case  5144:
			case  5145:
			case  5153:
			case  5200:
			case  5300:
			case  5301:
			case  5302:
			case  5303:
			case  5384:
			case  5435:
			case  5501:
			case  6051:
			case  6321:
			case  6322:
			case  6323:
			case  6801:
			case  6901:
			case  7060:
			case  7061:
			case  7062:
			case  7063:
			case  7064:
			case  7065:
			case  7066:
			case  7067:
			case  7068:
			case  7069:
			case  7070:
			case  7071:
			case  7072:
			case  7073:
			case  7074:
			case  7075:
			case  7076:
			case  7077:
			case  7078:
			case  7079:
			case  7080:
			case  7100:
			case  8011:
			case  8012:
			case  8013:
			case  8014:
			case  8015:
			case  9000:
			case  9101:
			case  9102:
			case  9202:
			case  9990:
			case  9991:
			case  9992:
			case  9993:
			case  9994:
			case  9995:
			case  9996:
			case  9997:
			case  9998:
			case  9999:
			case 16384:
			case 16896:
			case 17408:
			case 17920:
			case 18432:
			case 18944:
			case 19456:
			case 19968:
			case 20480:
			case 20992:
			case 21504:
			case 22016:
			case 22528:
			case 23040:
			case 23552:
			case 24064:
			case 24576:
			case 25088:
				return 0;
			default:
				return 1;
		}
	}

	private int getPenalty(int id){
		switch(id) {
			case  303:
			case  621:
			case  997:
			case  998:
			case 1005:
			case 1006:
			case 1007:
			case 1008:
			case 1009:
			case 1010:
			case 1011:
			case 1012:
			case 1013:
			case 1014:
			case 1015:
			case 1016:
			case 1017:
			case 1018:
			case 1019:
			case 1020:
			case 1021:
			case 1022:
			case 1023:
			case 1024:
			case 1025:
			case 1026:
			case 1027:
			case 1028:	
			case 2005:
			case 4301:
			case 4831:
			case 5143:
			case 5153:
			case 6051:
			case 6801:
			case 6901:
				return 0;
			default:
				return 1;
		}
	}

	private int getTakePets(int id){
		switch(id) {
			case    42:
			case    57:
			case    58:
			case    63:
			case    65:
			case    91:
			case    92:
			case    95:
			case    98:
			case    99:
			case   100:
			case   253:
			case   340:
			case   350:
			case   360:
			case   370:
			case   420:
			case   518:
			case   552:
			case   555:
			case   557:
			case   558:
			case   604:
			case   613:
			case   620:
			case   621:
			case   702:
			case   703:
			case   704:
			case   705:
			case   706:
			case   707:
			case   708:
			case   709:
			case   710:
			case   711:
			case   712:
			case   713:
			case   714:
			case   715:
			case   716:
			case   717:
			case   718:
			case   719:
			case   720:
			case   721:
			case   722:
			case   723:
			case   724:
			case   725:
			case   726:
			case   783:
			case   784:
			case  1002:
			case  1003:
			case  1004:
			case  1005:
			case  1006:
			case  1007:
			case  1008:
			case  1009:
			case  1010:
			case  1011:
			case  1012:
			case  1013:
			case  1014:
			case  1015:
			case  1016:
			case  1017:
			case  1018:
			case  1019:
			case  1020:
			case  1021:
			case  1022:
			case  1023:
			case  1024:
			case  1025:
			case  1026:
			case  1027:
			case  1028:
			case  2005:
			case  2006:
			case  4301:
			case  4831:
			case  5143:
			case  5144:
			case  5145:
			case  5153:
			case  5200:
			case  5300:
			case  5301:
			case  5302:
			case  5303:
			case  5384:
			case  5435:
			case  5501:
			case  6051:
			case  6801:
			case  6901:
			case  7060:
			case  7061:
			case  7062:
			case  7063:
			case  7064:
			case  7065:
			case  7066:
			case  7067:
			case  7068:
			case  7069:
			case  7070:
			case  7071:
			case  7072:
			case  7073:
			case  7074:
			case  7075:
			case  7076:
			case  7077:
			case  7078:
			case  7079:
			case  7080:
			case  7100:
			case  8011:
			case  8012:
			case  8013:
			case  8014:
			case  8015:
			case  9000:
			case  9101:
			case  9102:
			case  9202:
			case  9991:
			case  9992:
			case  9993:
			case  9994:
			case  9995:
			case  9998:
			case  9999:
			case 16384:
			case 16895:
			case 17408:
			case 17920:
			case 18432:
			case 18944:
			case 19456:
			case 19968:
			case 20480:
			case 20992:
			case 21504:
			case 22016:
			case 22528:
			case 23040:
			case 23552:
			case 24064:
			case 24576:
			case 25088:
				return 0;
			default:
				return 1;
		}
	}


	private int getRecallPets(int id){
		switch(id) {
			case    42:
			case    57:
			case    58:
			case    63:
			case    65:
			case    88:
			case    89:
			case    90:
			case    91:
			case    92:
			case    93:
			case    94:
			case    95:
			case    96:
			case    97:
			case    98:
			case    99:
			case   100:
			case   253:
			case   340:
			case   350:
			case   360:
			case   370:
			case   420:
			case   518:
			case   552:
			case   555:
			case   557:
			case   558:
			case   604:
			case   613:
			case   620:
			case   702:
			case   703:
			case   704:
			case   705:
			case   706:
			case   707:
			case   708:
			case   709:
			case   710:
			case   711:
			case   712:
			case   713:
			case   714:
			case   715:
			case   716:
			case   717:
			case   718:
			case   719:
			case   720:
			case   721:
			case   722:
			case   723:
			case   724:
			case   725:
			case   726:
			case   783:
			case   784:
			case  1002:
			case  1003:
			case  1004:
			case  1005:
			case  1006:
			case  1007:
			case  1008:
			case  1009:
			case  1010:
			case  1011:
			case  1012:
			case  1013:
			case  1014:
			case  1015:
			case  1016:
			case  1017:
			case  1018:
			case  1019:
			case  1020:
			case  1021:
			case  1022:
			case  1023:
			case  1024:
			case  1025:
			case  1026:
			case  1027:
			case  1028:
			case  2005:
			case  2006:
			case  4301:
			case  4831:
			case  5143:
			case  5144:
			case  5145:
			case  5153:
			case  5200:
			case  5300:
			case  5301:
			case  5302:
			case  5303:
			case  5384:
			case  5435:
			case  5501:
			case  6051:
			case  6801:
			case  6901:
			case  7060:
			case  7061:
			case  7062:
			case  7063:
			case  7064:
			case  7065:
			case  7066:
			case  7067:
			case  7068:
			case  7069:
			case  7070:
			case  7071:
			case  7072:
			case  7073:
			case  7074:
			case  7075:
			case  7076:
			case  7077:
			case  7078:
			case  7079:
			case  7080:
			case  7100:
			case  8011:
			case  8012:
			case  8013:
			case  8014:
			case  8015:
			case  9000:
			case  9101:
			case  9102:
			case  9202:
			case  9990:
			case  9991:
			case  9992:
			case  9993:
			case  9994:
			case  9995:
			case  9996:
			case  9997:
			case  9998:
			case  9999:
			case 16384:
			case 16896:
			case 17408:
			case 17920:
			case 18432:
			case 18944:
			case 19456:
			case 19968:
			case 20480:
			case 20992:
			case 21504:
			case 22016:
			case 22528:
			case 23040:
			case 23552:
			case 24064:
			case 24576:
			case 25088:
				return 0;
			default:
				return 1;
		}
	}

	private int getUsableItem(int id){
		switch(id) {
			case 5131:
			case 5132:
			case 5133:
			case 5134:
			case 5140:
			case 5141:
			case 5142:
			case 5143:
			case 5125:
				return 0;
			default:
				return 1;
			}
	}

	private int getUsableSkill(int id){
		switch(id) {
			case 5125:
			case 5131:
			case 5132:
			case 5133:
			case 5134:
			case 5140:
			case 5141:
			case 5142:
			case 5143:
				return 0;
			default:
				return 1;
		}
	}
}