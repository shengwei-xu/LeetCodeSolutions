package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class T1044_Longest_Duplicate_Substring {

    /**
     * 1044. Longest Duplicate Substring
     * Hard
     * <p>
     * Given a string S, consider all duplicated substrings:
     * (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
     * <p>
     * Return any duplicated substring that has the longest possible length.
     * (If S does not have a duplicated substring, the answer is "".)
     * <p>
     * Example 1:
     * Input: "banana"
     * Output: "ana"
     * <p>
     * Example 2:
     * Input: "abcd"
     * Output: ""
     * <p>
     * Note:
     * 2 <= S.length <= 10^5
     * S consists of lowercase English letters.
     */

    /**
     * 二分法，二分的是子串的长度
     * 子串进行编码
     */
    private static int ansStart = 0;
    private static int ansEnding = 0;
    public static String longestDupSubstring(String S) {
        if (S == null || S.length() <= 1) {
            return "";
        }
        ansStart = 0;
        ansEnding = 0;

        int n = S.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = S.charAt(i);
        }

        // 子串长度的范围
        int left = 1, right = n - 1, mid;

        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (search(nums, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ansStart == ansEnding ? "" :S.substring(ansStart, ansEnding);
    }

    private static boolean search(int[] nums, int len) {
        final long BASE = 26;
        final long MOD = 1L << 31 + 7;
        Set<Long> set = new HashSet<>();

        long val = 0;
        for (int i = 0; i < len; ++i) {
            val = (val * BASE + nums[i]) % MOD;
        }
        set.add(val);

        // tmp value to be subtracted
        long tmp = 1;
        for (int i = 0; i < len; ++i) {
            tmp = (tmp * BASE) % MOD;
        }

        for (int i = 1; i + len - 1 < nums.length; ++i) {
            val = (val * BASE - nums[i - 1] * tmp % MOD + MOD) % MOD;
            val = (val + nums[i + len - 1]) % MOD;
            if (set.contains(val)) {
                if (len > ansEnding - ansStart) {
                    // [ansStart, ansEnding)
                    ansStart = i;
                    ansEnding = i + len;
                }
                return true;
            }
            set.add(val);
        }
        return false;
    }

    /**
     * 字符串很长的时候，存放后缀字符串的数组会很大，占内存很多。
     * Memory Limit Exceeded Error: "bwvzoubsovtdpokbjgubpkzsdmxpdxwrkjzjbhcuvppjowoaapfpaqmgpkflwookqdujdfqsifdbkmwqfumwhotpdmevmshumjcdquspnihgceaedgxjgrtjdxqacznaluiiwrivaulamzyecdpyfyxmiavwkqvbybabmshszoslhxzhmaqtvhbnznnxuyctrbywifcuguutkdxchpyxurmskwbygnbtatoxwesmtzchjlrnqsjxanoemmzsqcraykiszrieoaqcwcwlyorrcvlamxztujsglqcwjmqvcmmuwwjgkdfhfxfahlefserldejymbhgdddlwjllantqxeivdbrsbjdcsgrcngejbfvwdjbaffvarzdkizozlszqvzsyszqklvinaypeqyppcyafzqkdlreddrmbxwiikymgcqaghptuvbgkoebirotkfdxcfxeykqvdqlbmnbpgumksbsdwdbyqfdwdqpfustxqorwpxyatjuiocqjytiqzymfkyagtxqypmwnppqwpoltglrpalpextaglktgzauzwxnzgwqfvxitoqkiqcacpcetzscypoumazhyjtqhflnihtmboxmqqqvahqsukxebqnyxpyoiczdjikslukjvgeejnirpijjkhdjrfkjrvvqlrdpzwfyredgxljfjmbtrkpdydmpqqvywtqmdcyrtapmqwhkaocdzkamlbunnfhfcfpoechlqripwsmskcprihcnptbqyqxdnhewuehycuaxbzxbftjaqsfqtvmfvisepjnrzqibkbwtncipwcejllhlqxylwzmquwqgbizbumshhoxwvexjxgiexmjfsrfnplybfdouqggsntludnaznsbnloorlwkhicbzrfvcqohmlzojfdsyzjniyoinfszmaelknerpwgmctnkjjnixlmxjdvpxjjbonmfxrxgpxrculamkmwwnrxgsjlwdohonruecrtuknjjjgsmcctqdfsttcikawjaowskacuretxcdqnkapiiiwezklfnbayedclkkdwdqqsmxqupcphgdmaldcnidadhpvyvbsprbrqpifrpdsjgwvoxumxeuybmcwbmuyacvrfqfkuszjpwyjwbmrhbbvdmhwrowsqahmeexucshsvknchkzljwoztqgrthoeldityevjljhavuqcxgwzcoyvsjoxvmlrxtytlomorbfkskzkfzhbhqjumnwzbfaalogdwztbeirzxitgbmfcwkkgfwapubspmhuygtfhsxjcoklybqoultxvmtactpqqsgewzzguxndxouiacalpopiovashsfcnftuaxaafwjhruheabclgjjyxpaiyuqjybpkdzkyrfnxfwcvdedcfqiseljtebxzcjqmqkhyiurdiybznjnkzaqthrpanhflnunqucliwsdttzbkmndrckzucllnjosvryowfkielbdziuqhiilbbsfbllxjyodgurluibsfjxqoupbxfpxkjscslirbszjmhklgevhdrqykonnnekacqtcworjpgxuraikciaburspaldpihnyafojwmclckulomsyyzxonthymcffhlxjfzonzxpwkpihianrywxejbghxrvsadkhljtubrenaemecmmvesijfqpthrkhiqhdrkvxlrkciaklncrnxzdhcsuaesjmbjlupwvwjfelxipbaligpeojbrvosyimousrqaenebgeleuuybixbwirltnctluazywoqcefcrejgtlfruyiotcbmubuqxbdthtakywfopbvkysddivfaaqaylpoadazdskcuhklvuqmbguwtlaawymbkveoszsysramyowewkheriglqwqejursmaiyhmnrlxryolizwsywgaizflzghtammlntphjmvlvyujgfsjlscpjhavxbeutapfieswtpldeeuxetbqphkqibwdzfeqbjxaryeogyirhleulfeeprmshdmiikfhuqxxpxdryenhdzgxojixhcjswllduemothoxzzpbiapvinihgucazbvggihobmscpijwcavhqxlcenuqjjjpehpreyccucfiecnyflxojxdskncvvdpwzrlwkfhhvgcdeevtflgwypgvvuzlntncpqiczyzxoxsfabybcxlarysptirnzpbzfzlagchruxjfzibrwfyxtyydathyctagcnokpwqfiowsdjvfrnzogemfchwitmoqsoezqdkwefjufdnvnhogqzzcqngkgaiwoqofwjtdygktqvmzcwwmzemvxlgryjjzoyguwjuczziosldbwwygftujlpzanbszeaakidazfpdtbnrowhnlqgyvssziolsfedivgsqvamcliuqeemeorxlrzyofbiozdpuobjwuxglvuuvxebvsnxomfziirlvhuqxjwunehjcqjnmlliqkywwmguloxmapflwhoddpqzduyfkizkneooifsndynggljsfbjcfzahmkambrojzdbatgtimxiisguvdpftjcwmjxbqyaltbgvfefyqwnminsuqraztojpshmlrqtrbpyiekbanjjndnfkhlyihbmxvwyrfomlpcsbrkrptkfyapjctyoruxdwrzjgcrfsxytlripxckhabdqnxwyiidegjwsqvqckjhlmlvdhfnlclizhdpsvmsrkbrzahcuvonpizhyrwilmbmwexzzohvlhzfcqcwhfgamikhkhfrwmymvxrmbypdurouthrlzubtclfltanroeegkfjqqrjxwgyzymcoopcubqvghnckoccjdicffridjupfylgseipqgbshoesjjcjhpyodpituvipopeizgwortgqvotiyfzfnkixcsbuywrxowetievuudmwtnxcbbxpotkcuqmxczzldimrdulntjsieciuebztdtfftligeybplqculadfvnqqxdbrnjqgptjlhqrutfvsiencukxpioekrszfgryxsbcaqaabbsvskhpivnedwejrnooejfvfmozywtkntdcpphrwnnqdvnakujmrqymxgetisbvllvcszyaojatpmogehyfaecerykkratgouegtfsetpywknkhzgzmpdwkjmvimnpjtloqumcntnhqxnbzycwrhkxwaxhweefmrlnemyoeygyxrbwpkrtsbzqkapxkobfoytbhvemmqhnjipwsjenrcegvbbkwjjuckixszmptqbydsigpatqsbybivryjxfcbmjprfleoxhfpdpzwujtbbzunbuvqaxwlrfxsnfhsereyewipnradhqocwyvllxfsyvfmdxmntdjbkjhewufyackxviawolgyevjfitgtvaitkexckrxcwvhqyhfqkjrxnmsgavnxdwqxzdfykfkohprhmiuzlrhnkgcprtnixgjosfgdkbecvwhjeghxbnshmmzqfrbeaepzujjopmfwwgwdkguunugjtwronvdwthlzipilehrnfdxlflyspxdounyhjdkbmdgprqqebpgszrpjwpzsfapdtgyunjgzgvernvkvxfwnxahwbawjrivczeomcjoecuckeglrmgygpqmrpraujjbcrtgdqvhtszwulmwdzxswtwmdpmcuyqirrfujrcrbjpxnklyehmsifbfgpprdtptrjtaauoxguntymzlqroucijjaqtbdipxcrikfbiavjkpapsgfuvgojbdklidflwdqpyddgylyfpkxjpopsnlxpfhngjhpsifbdfpgmtluxrevxczumrddonelqdtzrtyswsokhjjybjlfkoxeeluioxrjkjbkfnzoesywvdegskgbnnxenlwjxibbzfuygcvdrijueiegvapxhrhsqsdvdstbjjmpuotzceluhiqxxhowtfafltqipugbbubtixdgutxbbfpaqqrpurxytjbomgsncnvsibaedbtmvnvhtfsiviotjzikmkahwckcjgxdovdhjynlhhplbkklowfyfwlaobxazpdeyjxaikvcujspctbsaboohilrgrpmyuvwmkrutzlrjjmsczxdwestatrrthgkewhfqfwfsigntrpkxnfrrlxszvpwvodornyrjpeepsdaoqbqcfoptrjbcnjqyepnumdwwqzrohiipounayuklksidvysyoaquhnsnafwxusqhnqhqdsguuuopbnfugprvblrpnykxegzqqmcuehhsmbxuymbbvddfjmqplxqndjtvkwpockfyigspcryulkqunugsxqsvmqxfruegmqbdhrpqyofhjuichixisrqovmgvsaitcwvbouslnqnwbxuyldltrsbdasafhjzbcwsholeetygedllizzxmjzfrnsjkpphgulpkiaizbgprspjfvwoivvfqafqhfrtpmccrjaqdqqnrrjidjvpmbovopcqkeykqrsdykrenmrmgrzfycwkrmajzertyobgrkabhqrhrvtswtlinrwjrafiwlggeibhtlwpaesbdvqkvrwlkmyxdfdzocqagjssirvhgvqqxuzzzborhilzmcuwmdbbxyfhoveudokxrtgfkauvukprmtmtoyprgnghqvujfhanwzogstrrvixbkwadnbmddidciqeshyfmmevtttyzldbyrkmcwcvghywscejheyaegfihroufxjzpfgwynwqsrcuqufjxftyqozyvkjvyjaxutnheqzwqftvidgbfnmirwqrxnmwfnqmqqqvflyekoakzfbcdgesabnaxlgnetkmqgobnyplpndyaahqfghpeginvovqdnknoimrwyttackovtkpyyuzmufutvkjpcpmuhmzxnutduwjcoycjolneqinpqmltgtwfxddaksdmggdtdxhdsvbclxiuowmbhdrmxrexzxbmzvxhpnjssdwginukazqbwddoaryxsgswgphlsjtylqnkqcfwrcprhashjjgfqadgbvdlmhqeutwsuuohzmpkbqhlmwbnfgurkryfeiezahdccpujbatzozvxwwshfhkpmvgdyqcyfduruseuylxgwjsxoaneqzaxmacwixtnsjbfqhvpqscbdxeuuirwqudavgnfsmiuiedqbshrwirrpfodjhicsmikoztytnsgncsrchrzthxnvowckygpbyybzqaqmzncyictygqpuchnknpghotmnlbioojhiboigybydgcfxpzpqsmuvorcojgmaeglnzqgndyytzowmtkntcfahrseijgnzjftsmezyzzbawybsyaspibinurjnnoqfkmlkhbymxnsjzjsttvvacnzaliwaddeyoxwpmopvdnqkxjvffisnwwyhhvyrepxjzwmhzzpqoerfgzqkngtcytvtufreqbhbyutlgvrgtdrtekrzclgkisdmvbgimsvbtdekvttcjabqdecnlswfwmwwjhftgvslxysevchkpqawxiqbrjcrepconkeouluueqnvhxacwbrmwzobbqyrpvvexoqbskobhoppnyrudbdiewmaiipbijhoieeuqrzkwatwosyndbrwbsilhxvxtecefmcovghmpspmjfehnhxzfzdslpepbouknjmirbgepeyhqdailmgvygikoevfuwvstgfrzizqosiqokebsbmcuqacaccoldjcyvhqhzrhccrpfmefsgbukspuebyjmenqdsfwpjsawqgdzmvhudjgijbxgjzeoqvqvqxgekbpxnrkdsuwajzghcxetddxjwakpabectgbfnzgfkjixaaatwogwllelqjowwcqajotiifjyexfjugzrfohtdifulmmxbmpogesfojdytckpjslzmuhtaqxwubizvqkfjmeanvcnbzqjxwbdiiwamboxgrerxcbjiajdkjcxnruqoebllwfxmeibafrlvcxqjtzhhimiqgdfyfvneeonplkgelfwgwnomqtychtbdffvgxfzsihbfdqqlgiwvmyrnfdjfkzujejlyrfzcgrwkgvvmvzqsxepffzkylyggxoumvczppnlpqxsgajqvcraswicfgzlhfmbbvgheecnquozstyptypidxfhverlojsednujcqzyxbanuxjyssxvhtdougahbvhqxzczvbupgbxeztvaiwrsdgfgqitfuitjbyyvgblcfmwlirrgfknecilmawtkqxjkvjphinfiipiuaccdhzwliqmpvfxjpyibbvdcsopdhazkahsyolmqkzskdaxmvuurmiyepqeejotmbalmuohvnbgqrrryyxoyarggvdhhcfysvryioxfbyffmlunmiptxebijbuybenicvjrlwurymwfpjdaiesfmyhybqeweklmespoyavsdtexvaroeaunmwkivffmdtzcudlvhpczkefqbqlvqbjaghrzohietzavwvxxvedcjgunlhxsgowgbjzlwsufzfhlocajuhinqoqkwbpxsokwamhyxtvoivhukrtufpvcxhhwuoeawwhpasgulxvpxqflohryyknkubkceopapepkmzqympdzxomwzpymsevopnkywlwrzjykjrmlysebsigfiggfheofsatzjgdfzdwjogsfbhkpipjatdmhfqzxtpzexbijdyjdnriiievawzdfnmvxbtrqhxbvxgqtuokdtshcwoatauavwkaanwmqyjsbmvwtwxqquhabvwkkypepsixczqrprvivtbsrkubnhcraqykmrbpxjweocaywkwqqxwgumbnbsvotloekfkgnojgznmnevnwenslidezmivsipxskzlpzdfhhudzpbdxzwrjbbnogbyrwgjabqqsjdfyltfehodmpubkeyfnwxwdlbpayxogeanmghahqsnzpabyxacojnpzpueemylkoqpzmcidinwcdeepahfbifvkbrqlvnymzzqekikoiebubpajsfnlmjxynaunxhkkwcglfecisipcsqaypijzjuxnjlqnsfbmbhcxwsavglhsrhigjjsdqldfiwayvshrftuoxjwcvrmhvblnvmhtgmrpkvdscpzaiyqdyukfdwwziavtzoyajllgrcmxvwfzecfrjkvwaeahtqrqzviduvskrbleprfgtvzovdmrbelczysfdzafwsudzwemftlgluzteyzmcntqedjsqjwensvweprwgvtvrzjgavxdxbjzzifjxzbkcidqfnooxonbyjunibhprhlifcsrnadoukujkmtokwbdgxhvpbfumjeuzahtmnsbrvkttjxziumihncvibcckoahbhaphcpggjseymqakdavvjqaczlwysrkvextvpthyfutaetngqfduboilmwequaukikdmcomrdsfellscboolthxoixgkwzduqhsydnnygeqhjnnhwiiqhwxrpgmhqhfjxvdrifxchcczesgxmtbspeqlmjuhymffvkcvznpznjucvzngqgrxxvgvxbocqfwttehpkqqodjbatiduvtndxmujhgncetiahfzkddfkikptawxbkzzjensuwufoyyqfgzlkpfkazaskgevdeavhfcjtzfikwftaafpprclmvnuraehcunmjgdilcmyakzpwgvprnzwhheswfgcwejtdazfqqbubibsuplneetigxnnqzjbbyqsrsndrujhfukxluvqjwyihynlbjjjvnqrwusefdxjqostnwjhktopvmneiwlsqriobvjqcyjbrngsgsbcuaapxcqltkksshnsarzfzizlitonubgtzrkkhyoywqoyrfdkralbvmepptkajswfjbbymlodawovvqhcawlqupaivayazohogwibrxednbfvrgslxxgrjinjeqyyoidgisbvaoltxtghfmmrpdjdlkjckwhmjjkmajbzalglcpedtzwyoktwvmktnfdncbjxjbnzsmlfozuxgwmxmqnjfqjbqpvzxtmzzycapddkktndlmrqizldbzaavciitdxkgazfftnrpjrbhgvcmrclzdqccosppcyjwuxwnnhqfhthgrwfzkngchoyulklgolfhobikldjshsbnuphjerjortnhpactwruunlaobjfclmphsixgsxfpuizyonduhoqmncoeoxwhvcviyrpqehoanvaxsnvdgwqryzmwcuuadecpvsclxkoitlgyrochrfeidknrnostmbmnfjdcfbvclugdupenmdqcaudsafokyhczbomavdxraivgwkcwmflsczponlywoyxbxufvzpmzporubznjymwcatqotlinabpnopyvpaxmfsmuspqakozwyvoyyraizusgcqukxbdplvfwhfjyypesmukjvtwdfwdtwukkjsetcythejsppbbxqwkzqhfgxzznhoqgdmytdlicnjemmdjjolfbczrvqzizuuoklcmooguozvksarpkuvqgqzwacrjfenvpxwzmqejjqgerpevrmthixhbshrlacrlvxvqonjydlzclmmnmiaiaukmsxaabragyvizjmhugkgjkdfyjoywrycrgeoiqsodnzcgrtwhbpbiqfalpvzkcitwjcgogiqlaemehhuherfyxegnoapqpxcrwhptwkeebzrmvsbtzyyprhrbizbpowlmurfnqptykyeqkyqtrbpvwbyuonoijdzhbgxphgxaxqdsvalxoyltmznyquchxhfudtctwwmkxzjrixgcwfaszgnweopimmjzbznjrjvjtbrwhclpgmtkrzsycqwqklhupillpskebbhlqssmakdumzpuzibsciopfvwrlkietropnwkputlrllfmtpfigkyjfmjlukqotugzqndpviipusaluvdmwubithizeuhfvyqtyvtltanazhywrmnfqxheehzttlugahkmhyuvgpvbtvilqauivbaclcptvrtwotygxlopjcqxtuirpldquwefibdulpzkpavfjxdioypshdpcdplzwnfznojszggaigdlavwupvvoyaewuevvnfmmkrscjhkvdqxgatthiruwzswgyumkernhdobpanbifcpuwecqxafjlbgkeorkriwctvbxrvfaafzendmigwqrfasnoaqddainzuryzmzxmvudulathdjnzfxysdikjzzfloornefbdwajzuydrpuulodrivwndaytunixzsfqbhoabazslxdcqkkczfcdtmfxrkohvaxkhqunbuljmuthlqojrxzzxsuzrtnrsnnooyrbzxglrmscdxtngwxxxcdgwqihrvsyqyohwksatsrylikstjmjfrxkioaqhtoxxkjztyeuxulmfmjsqqxovlwctjbndfvjdjxdzveuqczgssidyfltsitaogaejsxsigbmglyvfvmqzktldwqcbzykrmstwkkxvdkpsnsjqpcbgupghjqokknhaxcodgfmgmvsccymptmiizkdowpegfbmuqnxexgyamhynjdihlptvrpuenkqvvwrludyoilyrkrvyazeqpkgojhdqesecdyatztcwqsqjuvhgiafntuyerqfnpzzoqxkrpuemkfaxwuudtcncrqyzseentxelxfvxelihnkmaetgosrqzlybnigoyfudygeknupyashjzjydmlvbeftnkucvsvvjqjkzesshhkttjnurxjnydrmyozkxhqjtmxcaiphcvjwthvraodjrxcpthkuosqsphewmnteirqkscltemixutwklutgahkkfnoqfbdhrtfgzpuyytdxnvmwcrcqwlcmomokwvmevcdcjzkuukvjqnsrzsntnyjsrjwcorcldnmytptsbadqfjxyvkyhqwnvmdtdbnwjqavszormcrgekhvumooushqainmjnsmgksvkrrkjhybbjsbxkegbifahaamayykhwkbmqrawgmmrgcaoaljvifnpaihejvevkmbcnkpmihqgtowhlyqqvovveozdgyaawvlpkyniqovonyqqzacfvguctrruafppkqejtksakumqwcpwkjziwtabedujuyryysvyuoejxknqqzjithifvltdxydazozbkonccdfqeqvcolwgdackiqkpitvvcncfgkrfyhvsbzwmdcxnklguqrdnmkoeavczwuxiosxamkjytgpsxgidjgtqwzysxhyjunrzkaxkkzybsrewdebnpemzoswldzgelouabxgvctyhswgmsrtoavfsdhnvdhqnxchrtoqbcvyskmzdailqmliiyuwsiaipjsroiqswvjrejtkdsskuifafphdugsjdyicycpcyucuzxbhhhjpgxxyphoddhdtwmrvtxlqhvbgaeonywqlgshofihaipxrmbsvxzockokelzyixrmigencoiscrvolaolbsielzeytctiznwiaavocorhggchincedilqanigqdsxwawacupeqpprydkhomlozwdkxvmdvcckjhqfqwdhunqkkkhgnskfaildedwffelzixmwcprochpybawuztwbnygjaepsqhastdmltlftwwdwivofyuhepalekzapztqdqkbxrkjucakngjftoxjejkcbapyqocvxfccvvbihdhqtwojdtyplkqhrtbbbcsjfymotwmxfcrvxwqmkbdmndhhhgebaybdevujanwmtlbdijshfaqeiwrfwdhljthoweotzefqpvqmxfmjgsguafwvpdqqcvzazvaniflvzwwdjzpurddgdhqlxiqvdcrdtzeiuzuhvrrbaugqkedctuzybpygspdpaolugvnvxqacqdshabauuvsoaqfdplmpjnfuooyejyhacspyozpeaqjlbqyboesedoldrlsgoxneleiyrqmmzswolhnyzqiqzokxyuxhzhbboibzrfeuuvpwxlxxiuszdnowvhhakcbwobleewccjsataahndxonzkedhffxnoulnpdvuvrcmmjvkabouyihloznpfxigztgisnooyejuktlvnohsnpwcniuqqtouxvqvzckefsnkfkhmfcqbbtslrcolgbetcbqhjzfectddiyglumixkxtluqwegdchcywjjoohlcmpajojtaxdhkisqbxxcpkxolprajanuewhivzqifuuersixrlplhvtegrtgcyshhpsbtuckaqwwmtnzzbxvczflukbpttkioseilahdznsguroekkujisnxwadgdisrniqopbvexcxrfdbepcbqlvofkxdwwlhswihlbnrhvzcftluchdcfshehllpgagmneatwfzwhwngwmzekmorjegrkdznybbhpscptytahzohacqpzbufplkynestxqydswnyjmzllwqvtdbrzvsvacetkndvanvpzgcyihovowzbaukxvbmwdtspsroleogpdcbqfvxqzahvmtdptawjdujsuohfxfudajcrrwwfwlqnekhxhstsiypcwukvqmdefylyrdzcnswtbomehkiqftgnstabnndnjzmdkenagijqsqryptzckulhlahnpaynyebrdrgxbixvfompzgeqmqfpoadodyxlyuvxxoqdtyrhjhjnpmulfcaifhikvbrgffnljrgoxxvaqznaljqewnemxzvlbfwnfbxeuwlituulydyldwxrhjyclrscurtmqkblbujfabdbydhlhkpbgxolvtvsonynizfufdqfnjkquojqxqixatduwblelwndusvebllrqwofcfqifbthtjzhauvaqxjedudoywmgeabpbuuxbavurpkswgulokplpwkowqerlsvsolnwvnspdgzzucfucfvelpedgyonfxypqtivqffiqqhvjatngxdfbrcgeiukgpijarqeirdqrqevzdfdpyyrkflzgsqiytxyrpngshgojvmayushwoggqhpbvicnruelgapfhvqxmmkywzossnzyannddarrhjkllgcdlsgjtyykffmkmxezihxygeraopqsruvglgrgehmzxdqrfymjijnftbkkarmzcsblionmsszfwmpebibdconajzisdlqzvraiqvgcdnqsehalgidsmuhzrwrsdoopskjfbexoegnunzbkcukduqtxslbqqblhcdujfmfefcgpozvvderdendtlvnpaxmvjmzsmjwxxveurljzwfppocrazypgjlebeivxluxmgajtrcrtehskckbjcjdeyqrsbbgcnmfafexvqfjyctxhilmyvsjxtwlevtlxeboqzdrymseiwtakokozyvkmpckjhwympwwshjceyoawuflpdcevizbwlvmiyupzfxmembggcnqlbddnjynhzkknjxrurxxlordndjjwgqbfdkicptvioneieuwkzpdmhaihpvqeernjxclyvvqxltdylxghdrtviopsthrqbereoysiwpdsvylswuhnnpxouomcofdjuujloanjimjaeuxinalxwlahbigqkvwxtfoarqzkzriunyqegqdfwmulmhqxziiwivxtizbbljbstqqfdycauteotzggiqmfexcsudnnxerdvihnojblnoacgwczldpgowrsgkjwijakyagizayoimslpqntljdjeabdtcqlglchxsjkdepmhvbgyislhuwqkxbtixoqhheerwwtfsjwlnyldkytnrlukmbjqvhbquxgllgjzhntpxjcknrcaigexfygvyizucvnexqqqcdpqjcikubbspurvkzbxfqevyviioyvonvtxfmdlcpiysxpbcakdqzegfrjwwebtbkwptthaxqcrhrgtjrolrppympiixujmtrohrpnzikakcvgimdcogpinqhchqjeniqcojzziiiqeorkkfbkextieofwrnugjjyfzrwvcuvfkdqxlrvdexlsnddatcznwaifxqokxxfkhhtgfvhasgjcxbvnynypwygvvavkoscqhyktulgorqtnaxdghiwsxelwormdwijfssvguuhhujpkhudhgjicrdnnfxzpbrtinwuhttljsftzwuyhdjihwyosehuphcqksuwpxjqzybjgbcidxaumslixgfqmmgqffysyitkdvtnbczsodwyrzfdsdbofupwqxiqkywldivvohoppkijcdptiuleivqvfoayohlzvsxzgowlsndyavsnfhleepzdxbbawiuicohuxrutfkvlqxhuffejkmyrpjxzkfupllqciadtjhnuhukghhzxoiioqrxeydeqdmivbdhzlrfalyuxsgkdoaiupgatfqeqghlgwbldqvxidwekrvsnwvapofjtxptoltvackkljsaiqsfolxydobuqwrsgokdkjemdrdrxieimulmiovmmbtuazjcepfjiqzorrahguiycsonesaxlmzrkeoxywyxddqwkiyabzhhxjvhmynqcdceietyekcxopskltndguoyaqsktptlhsyfqvfqrnystedyfogjrylanbpiwavidpsgdgvhfdesrtnoofydenydyimtvahliycpausyowmixnxsklbgldgqnijlfxoiluqrlgsedgvervafoclccinzbrozmywecbincqwjjasklfrrmkwsoglmvdsbvsenjvspadybytpxeypogmoncakpvsogrfobhnmdswzhcxalxnrsiodmmjmusamtooxkfmmwxrreejnvmwavgqzcimkicpbaofffhfkclxjswobvukmkorsmmbsbztmmqhswvgimmpawgbwzorprefkiolqbclpxvvtqsuyxfprapedncawrvwyxyjludqzdtbykkgzzxelxezmieehvesbaxmpafbanfdfvdmtnoffnxmcefwvgwwdjnuzygxoeddviemzmthatigvqijiauupcjpzeiehyexpfmsvbjkvsakzljrlcvibcqvwcmovhbnthjewzsudiwhqahnayhnnfohqgdndnigbolnudwjjffvivjfmdjtsakricvfnyfcygrtwlwggqwlvrfuqwjgoumbldgqgehvmhxnbyxtoiaaxgxhxhfdqykaevpinuhklghpvgzkabnwiwcwodqwisyrkpyowstigluvebgaiwttwnzkuvmfcpmsuwasmbmvnlposzbupkhzpfgygbozivmejhhyvjxshbuuxbvnlzqwzbhjprkaqeogqxzdnsmkrmvbdlclnevjffzjodgkgisbuovjgsnmuhzfarvjcoxsxqjzcllibqcoihkgpprswugezllabltyivzwwieyfqldjkkbyfppusznvnsiwptritmoyczwnipfbllautpeydblqazgvydxesdgntkrvlrwxnujguqrrfnolugpxwsvyhivhraenemzxyxxcktrmhkepmpovolpbgmxblpysribkximfjmrbrbfufontaangpfjdbqkcvvwafquakdwiqttlqhohsptfyfmgvhzenghofcwzbnyonpzgcsgmssnscmoxveodvaldwcskxdrhpetxzihdoqghvsuxrwylvztmcyunpxcnzurokeffdkpeiznmbortzczkmbjylzajfeqjeacsniqpjyoebergheioyfhfucgpolqsykwdwqnuwgbymyakblhneaeowplgyewjrefkkxgiunkhwrcdoncooaykfxzylfgekejarojoxhrtrzkxpfysuhhezobuvyuxzcjzelliywbuohxgcyegvzucocwiihfqjbuwpulxngaxrrurbcytzwopdyippuyxhjruojenbzcjvvzjpctwkuflohskasumphpaxfkrzwqhheqiiemkvzlbcghypfmjnsshqrthxajdvixxfwvbhbetwxvaeqjduecehfzkofsobchrdqpqaouglovhjpckuclgjnrufdmaqfxookndntemsossbgrdahngewypzlhcwxirhyboeihprbackuiojvbleshhtetyrisexickxtvtxwuyajksnuwqbxuodjzxzsnxpuptqnrufwfljscttnoixvveiutgwyzcyubbugwoipreroyphjotjiefupxsobwsjdfzxurjdodxqspslsuotgltvjjmpvrfxrzsfbsjincvrxqzbaxnblvgvhkdkkjawfdlkfryipruwamtpaqpvplyexlkzqctteexoysmofgpthdyehcqvsvxpipqyiofnopxotekdbyspqroopohkkzqcoikuhvktwrgrqncgvsevlawkzcuudxzihfjnlvjvkuczvddvuiabgpfrmotfebhwtlwigzyikropxnfvtlvmzkzomltvpyoncybbdblyytyycrfpsicgsvzksqgemejeetvaxravacbyspfoylutyryzcewfadqhsnqlwwhzkvhsweyvvjcxfmmceqdecioftgdtuetdllhvxqjgxrhtnwqicuzvebslytyatcqlwldvbdkiyncvhpcaglnykzarxvylgmhabrygigthtuihniyrcaxfcukuocekcglwoukubxchvbwvvcpjbvfungoyazrvzcirlpafbwjyhjizxxlvemozkbwahbndlukmqstsoncwyivojibarktzzxqmepgwrbjjtperuhjyychghelbihbsqnasgnjuschbtpgssfoqwqjjicfdzrysuzlvhdvvqfiaxgohuubjemhxavfucupwmmadihyhtqcdzmdlgabyzgyyjyfxbydaqurffmggfzdfnnqmlmllaousvupvvxjbyxddleyudclwpzwdmbnrnidgrdidszdjgmkggzlissorrwjfddgixeomcvwklenifdhvgegyqfifxzstwioxcgoswuqjtqywwquybujwhiypebngdiraqcueeekdkvlgzzsaemrmerokqcrqfhzjbuvacskujlbyuptandooraxnqzoitortxakjivjndbflestdctbrqsdyruvwzujwsaeldfhhcdbfpqkizwatggvgpikezneqpuryzkdkczjifemhfnccwvrulifqeuosnmhwbkcfaeqbpetlvhgigifjbmpdfivlzatdshwxvbvyybpskrdpqotyipxfsohxlxwruavnbcjlsbaciogqvkjjdowmbcnkfjfltnriwqemxekjbmylvzvnrgxeekdepgkfpoitperbwartimmkfyjxpmpvwgcugtenpacxxzymybrtqtfduplmpktuivwnlxwfrvlqyorhvoavytaqgijhrgbuynfldqtlxnhpfdvlbxzegjkyfplxnxnhwexypdfudtyxjwsudxkzooidthzkuoyqnxjpszyynfzzmuaofusvfqcvxhrbgofeyefwpvplzdzydsyxdaobdkperkxjugpxnzepxkvhaakkydzxaamtdlvcfqqtnmgkqbxekihgigxelmgnkqpssdhjjbimnjboboiyxfsflnuxcpqrxoejuflkfjwicxlzdoxxpemomauofvdhrohocaapnbjwbvlrnscuunxfxhroqhfjcgyaqqnwszvrxqpqdkjkymiygwcnydbukbrbvqdtkpjyeeqkxiswkhkrxcricwhmqsauiztyzieubkgalgduxdyxermeknpiitlnrhocsecfzrgagzsrevawvhtcsuqxccusudzrbbtgwkdwbkbtfklshcapanqsnjggugrhywtouppmeaheuwpriffjmnicbyobgxhgexmksnurkbhgmahheuudqaulkmyeubuiyakpftlbfcnnmframtjghcyeegxbngnwmifflhsjnsgacfmwgtcdzhrrflolqangavivxmjerjfhyznewejvtjvojhvprwtjdroxvjpfzfcrdsvenkvgqdonxwtpqvpmmopdvnatqksfmutmseevwtdrpgsocgiyfzmdtgboaadmfmslbsfcuvhcxwgpqxqvqujrkosfduvxfjzfykonzwqvzvzxkzqigppuhfcabhmmlfdvubqcbxehywjltjyprzusjfpvmpisprlyszhpeyyjzzorxtfxlalefpszblfkqgxvgiubroswiwaaksmbhfohtqswlstrkooxwgimdwymiffoijobsqhxocipnriklewyjofhszrwrkjxxryyyfsdachjreudpjcqdcbhefxmjagzjkthrojpwmgymdvfuvyqkssjsnnwpisqkfmkstmbdjoeoqblyntvvhkiforfhifvgnwdtxtqdmwmjyouykvublnirayriijgxokgwisnvgnucdxjhjhzqxxgriablmloyzbbypzgfgwesvkwjbhbkrhnwxwarchejbixmocevuoidpkqkcwgbdqhmnxkdngllyrkuthaxkpyzpcuqqngipinovmvwpduzzfsmrvhrnamblypfmnrkzclyuggwgwtltmzruyolblwvkbytlbjryukcyknrpofhevilgzkqoxzcysydycqxttegydqxkypovobxvohxowrgnjxhollloxxmvqpidclnrwwpmisswtqzoosgvjkykfskiqmevegieatdhluvrffkhxinqtbmwvrprdjdfkeadrswkxygcvnjycnshbnbzlrohwbgimmqzcflwgzpdcmljttyzswzhjqjrdkzeuuauomgfvbijpxdvinhvwjrydoilzwumsuqtssjvlxojbqldlxsfqgswbuteejiahtrxjlrmefchfdjauockaewgziuvxpxvubqfwpsfalieclziczxthohobdbcehprhtfzcpwridippqekavvbkimmzphaajzcptduwjsqftcgbsqdwlneannbbtosvrafjwrjfsfabmourlzjdvuhpqeznqniybrcnblecnrdpmcimlxmtycvhphrzmltghdbgghqpkqnuedsqxykmbuuhgstcogoydumkaomtvhpknjbsygztkadowvqiptpnwwqhmxmepbfexjammwoeuvvagsbukxzosrvzrsheujrirwzasokqpaktgjrgqspgjnvycywucyalewtncynffzjahkwrcohbthdkxyvwrbazdsjjttwbrwjyphoxtqeqgnjlhypankvvfmeofieemcvgpeqwtjvhjklpgozrcdwjptgdhlsuegnqhmgmbjndcmsaupemiqjhqoojyeqtlytdejqbmktlpfqcvkagozgbeejxalkcfxzfjrzxipbhiwbfnhyokaapigbspxattkazejeiwlletvsnbkphalnrzazfwnppydulfiyaeiuyqppmjsbwjznhjiqnzrhiaifunigjitrjcgcjyqrzleyvnugsuzygdqodaptpzhzoxywsrghkzqpljvktzcbomugpgnocogbaryhahdmsvmrmdpbeqgiobgwuptuppppsbrvrvupxnvfcunlivhhnbxtcbebhukbvyvvmhtcrrdtqlmvlmhhcbeplsibaxhyaempzolhfcsxmccygkwojjnarmzehpixeqfhmngxlgcxrjtbrbwsemmtjmmysvpriawtxjgoaiccdmwcxfberbapwycoplmtjfavmdtwtorvncgwcbdwznwhlsfymnadyrsedpnibafzggvybymyxpcglbbqyrcogjntkwgjxboikuskrkmswigopiqfpuyvlismrzhravhujioylsdtnefsjvpfefkqoiajudnlxknllfphwiqyoaopojnrpagcdtuwlduqtufpuxxvbpzaxlavecohmdqqmhtdilkddjmraclqpbgzaenajssnpvoeowmzmcjmaxlnhuoyrnqqaqvrkswfvexzdlrhrxzplcwfbemlaoagvjxohwwpgmjormlucxaeqfcaqgszgiavokanoomwxxhrzliatfjwxsncjryyepknrkbwqewdkimfmoabppphnppcpamecapslkgjgqsohbmfpeixaugvuaeaqsgaxclgufjsbwehjlarxnblyryzlnbmtproblkuguflgemsitlslxxovxtellpnwqoslkrxrlmosxvxhuhtwskpblbjseipppiprflvdljugbezzjinncliuswdtmrmzqkstlinyxjvkmegwyrcpoujuozsefcscxaxeosugkmvxcupjyvyijdwlswxizzhfsxjinexdztvbslefecbljjhrlutmtrqoivhbpagpcqgjmtbetmwarhxmesmhbeclpbalsbxkgsmjyytuwynkunhugojivvniysfqofccqyaekvcndrkdubrbbpeodqtdqeebiwgzfwtyofmffbuqkgdtivuyfvqauihaspbecowgluhqnfickixbkskiyqejvqptsenrdfbaouqqaangrnlxaskfwlglpobdyafdverlrsdsxycfiiwwjpaljbqujeqjoezilfburkivrexsaqmeyvkprhinmkvqkkxdcfpwvmetgywarjzaxharauhuduecocwmpmgwvrpbbkusyxamrkuigwhaxlrufiolnctvscthrmxthbdivhggeypjsqhkrzzswvvorzgvlibiwvogsoskydhijleeaikmpeaxcytccokanquirbkegytqlmsjlttynmozlehauprkcmxeynzdmsxbxjvhasqwtsieirfauwxpifdciklqcwxiycwmgulqinxhnbzufzjbnmbkwpycjjkuolsfxnrzwvqbvrpaykvddylehrlexkdxqhctknrgghqtsuzimpcpkjzuqgfzaaexhtehmtuzfhvriuyaoyxgyvmibrwseruawlfabwszeukixxfoivkmmncnnejwposwqnycgaytolopuctknniiebxzpgtyyltqpprekbbrmqbflwawimifoqspcxzwnqdxgnpcjarsaqehzhsntvcmquxdjjwzlmixivjnllgmbjkwzxvvmdjasnukmoerjqphmuvatpwoildncsrzgwwcyoxcidxdkoislmuinhjmdowhzdnhdpptlyvpiusreczseilrqiwpbgqtafkwgfnwjnaxnjkisbgagtnksturmdhiwkxaqadwupoawaaigjhefpiexhnqdkijxcpyvkssobcagkjadopuwhbmnscuagwjnjrfoogbhdudrghowdpumnxhlqejlgrteemfhdovmtqpmxpnfthhbmiednkfvegpnwvhojbtdavgwfmkpetdyrrmayiikgagcbeafifxonpeockevwlfpkmqrcqinigqianomjtorcezfzhknpdpmnwvrsocqdkoodpxylrkitpltnkxbwefzxqqonlihcfsduastaicomqsrzlkbszicjncpwzjdoejiasaebxqzizsyxkntlyagpgcoistuejmhbehstbwdehebhdloqwqbyflzqdxryjypewxocllmmagdkugwnoamwmtklmrckvaqpiiljtucjwxfvzdrolwguwhzadiwmtnowhjfmooohgsdsqyfcjkedxrxttfynyysgrmfzvohyxfavxerxapjphogpgeqmpikcuyoysdvzyklbotpeyzvkmuhoouxemupyxdztxeuaaxzvncfxujptvevugewbojskkmlifhieolztmvzugkcznhdcseilwaefjvqqkqgzldzkmdxsykxqvotosayrpfkzjmfysgafycglxzvnxhjrlrtxeodkfnwjyfrizppntjngpypahhuhesgkxktlqybdouzyasngwgzlhfgikedyhitdjxzshfhzlbweevugtrljolczhaxecglqgqlhosgywgosuvrhmwxtunrpirdksmfrbbxlfgoknzaqgyxgciszicygndcncupbwpjvyzfywshppnwhjobcrjfzbyjrczcdalsnrwihkpibyznznullsqpzseotnukhfxpnivzrloyfpdauppzyiihesvmpawrjpszufdomrzygnvmqpjflzpdepmoyznwbuahrulnsftnwvtlucgohbyfmohmddsfuunohdmiwtihqsnmiwyxnfpsuhxiydvyjejjwaxyjjdyfipgtjrlrwbgycwvfrljokekkzuvlevsrrmhugedchgpzalnlycyzpocemrlwgakizxdxhhuwhxbcvqdjbnxlntuzxjoljtynfvdsarmxowothhcjpjwdedrjueiczvhdhvzvjlavxuschkxyidwnzcaucqhnxayohehheveamijgvzmqnubtconujewfnggisqpdcgymgfcwfqcchtvhvtrhpsnzdvwybovbgwunywrsivtbyfacmxjvjxajsplpnfttlnhbyxokskxnftmbaewjijqfmehtcyxnmdeackmjtrydyisytwbegsymsahlbveuyiybaabrgifjmyepdecqqfkykrgitfyvifswmjcezxcdtrczducxdyjiapjggejuxohkyxznmgrzhegyzxifejhcanbqnrhljgwmgtywcnffsvkwtytmyvccshycrjctiuzucfpthxnfdolpulqoprlzryjwvpfbfizmmapevylmjrlfidsghbawryhghumvgznjcqmtijllxvuuxpiofbuhdwyeforrvcyymmtwkfvkfomqybclteoqfifwoiedbxpncseeuasnemhcxtzqvucuveibywaacawxgplxfhrsqiyxaporvnulqmwwvxtrwhibuyhtdlktihzmnfxbcmzecvjdpyokkiizcecdoidmluukzzjrddhtdvwyeisxwsdoxgytgyqgjgjcoxzvzhiszzcpqjznuoguzpbhhbucygoamwunmicwdvjkvydvfmdbqqtbczugehpwcldknjlarkodhuzphnnmrgwrieyuxustkkhizavkvwmbseznguvcvwijciamzksgvghiqstgycaumqjdfzyedoqviaryszppvpnhvqfvkmbrnwvimhxgxdkxoplewqlswlwbjvrxharvdfvrdsasqhqzyxndztlgmftfkkteoiheuahuimihntchzhctezhdprgfpgfvlxxrcemduvaqifizopcbrolrkqkmngqnkjhpbuexfyzfyejsosmsqbpzhshdotksgnrgoahsljcnymhetufasunglvmuzwxepmdsuwoedlwndxsztgeesdvcyjrfryzibqiuntezjegjdiqaeogcqpzxqkmbaycmwyyzaonhxgexuplwfiodlcyoiacldyuohvupvmukksxmhgwliclayoyrkvhjvbsjjdjypdluoakdagphdsfncalpwvmaehsargabrmijhpvknumtjgtqzbpgegxflzgblczzqxnfglxlogvnggeyyjeebpaapvuyaxpwtrlwxytdagituuboemdifgehhgmsjkatwqoskqcjetochkmittwnkqnhahfbuiedpteumshslhtxmkgncgjltbqdijravxzkxxputqruhevuuqpdurguworxhxkqxcqpvfpwpvnzbxmjgxrpajzvdvtgkwoefwdeaotbeapqaqupkvoiykluxrpzgdlvftidvjryglmmkkjcttfqaryvojpgyfeofwigqjzlrnnhcdstmjqhjsojqdgndrdizjidkrpabztvnelspwacrnjfwxhbrqwcxtwiykpfpxdkmbkpioveuvbdmpxnfnrhpwksauprqbxcxdrfcjxruczzsdzprocrfhpkckjzvtodavodklpqwqhkknkrgqwbiqpyjikvmjxsxmwxpvxulovkrzpezdvbkbeqrroxdkzudnphocodenlxraarmgdzymbwavbscjxjynpeucanhefiatkpydsqaxoqsrsfryhrilsyghylpypsbwcuahobwmuqrnxwfscksigmymbajnuhkeksbcbqcfnztzahgepabbywkijqboolekpgfgdbckylcyhwwodxusesbbhkkpbtdslnhkjbfactiuwownbgfezvfhuyrlzenxeigtjoelhcvrzgcppchraxpurzohwwiroreedqnuowrsektacvzaqnjblakqxtwyfomgcskrygusvuxjrtbppwvartyuqoqammlqsldzstbutpkwvochacifhoferdidjgdbtzceltmdafjmzwvkdjwbkubbagtwrwbtlcqzimvnmcafixfwvfhmteaqnovhbcgggdzfodbborphlcwenrrntssgapwkqrbbeqishxrpqzgyapovriwktficsfppvaenbhfgiqxkwsvqsbqseotvbxytzgyfqhhvbyszmakksyzairweznjgkdwctmpwwnoppvqzfahuiyjoebswovbttcnqwjeugniaerjagzvgmtilhgizbtgozcgqgycrddljrvhenlnnqjqpuuplsyxvnyinkxpxujdheoidqvphvcjwgfqubgniauuiysrfnwfuijytgkfzdpbvgdozfnfliskqgnvbbvkmjgcdwqcmykjmnynjycbvsigpwltmamnwdprcuckljvoqwqwjksfofapwehplowndkflsrgnpjpfcloaftxqbsoyflfmoxneseiiaqebncnqinzvcmpgaajifjkaupuwgssgriafyfdzrbagkzmfgpuoaycrabqkzuslhcicpyrukqtqdmlorgvuhwdyobzjhbiqdtijdnjszpohctlrsqclhxqdcuqivkmxztxqehgroqcmrawccmbivgyeidliugietpmsxqxxvczeafmwftevnzzdsowrnracxzlogrwhqaqyycqhrxzprwlzagaaoeitlbkeivpjobgmogbpwhtrirpqikaqypysfhbdjjpteojddfftmabvbwhkrpozxbhbrvglimnmgdqasxkjzvpqkpzmcgflsbsshuvjremmrrtbnfknprxppncgbkybhfcgjsrdpelrqlbnqbumwolcxexjrmrdlggrxxanguhajqoaiwtykjfgtocficbhdhbtznfvlcapbvzdblrpymjuibxkpbwjsuheshpovkfoqyljdievhvcowtuykyuptdzakfcrxbyrkiuhrxxlbeyjcvobqsndptroqancmttbyyvufdwqzpciyxoabutzzhfviblyfausqpoakyhlznfjvjqlrcczubqwdfsavwogvifglkhyfglrrbehghbunqwrpmsaaxiovgxitnzomblnxrbyowrwggggzpizgxoucsxxadlmmrcaqtckjvzhssztiqpsqofsiwyssavscimwjwjcndpirmgdgjvyydvflrolgpqyahbdjyacwqjswlsfjrqtwowbygthdkgmyiurvnhwkhvxxaaidxvwbnfqndglqucmclnnqmtgpykahxcaizbedpyhhjcqkyvdovfrxgpnasncgqstokqnlwcoorsstxkbyprtnbtjxszmbscjlvnqihkzmqkqnripsxposgfedyjykrctynxerlwqalsjfiinvkunsnylmfldylitudkrzqsmkbltutwdpdqxounovmtsyuelvihmvgukxxedbzuepryjsqsbcmtzdgxoysgoaajtebdcslawrptvazfxayazcrxykqlxzaxfxiqegaoremtshgcewcdluvydrhwutcomhdcmdsopiuwvnuygalvosddsmmxezgqozfedlgidiwbkhvkcgesivrgvzmqqiavexdmdrgrffhxxgpiqwkujkjlcolcadshnfxhfnofdnmqnppqaiymadkuepightrfodmauhrsyvuhctzscenlqbvfxggmygvtqtvbhwdaoczloprvfwwmoiuuvbsylfurqobkifaidqqmhitgpynnnhmwyajqbcuabjlw"
     */
    public static String longestDupSubstring2(String S) {
        if (S == null || S.length() <= 1) {
            return "";
        }

        int len = S.length();
        String res = "";

        // get all suffix strings
        String[] suffix = new String[len];
        for (int i = 0; i < len; ++i) {
            suffix[i] = S.substring(i, len);
        }

        // get the longest common prefix substring between two contiguous suffix substrings
        int maxLen = 0;

        Arrays.sort(suffix);

        for (int i = 0; i < suffix.length - 1; ++i) {
            int l = longestCommonPrefix(suffix[i], suffix[i + 1]);
            if (l > maxLen) {
                maxLen = l;
                res = suffix[i].substring(0, maxLen);
            }
        }

        return res;
    }

    private static int longestCommonPrefix(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() <= 0 || s2.length() <= 0) {
            return 0;
        }
        int len = 0;
        while (len < s1.length() && len < s2.length() && s1.charAt(len) == s2.charAt(len)) {
            ++len;
        }
        return len;
    }


    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
        System.out.println(longestDupSubstring("apple"));
        System.out.println(longestDupSubstring("aaaaa"));
    }
}