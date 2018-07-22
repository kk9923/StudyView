package com.kx.studyview.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/7/22.
 */
public class BusLineInfo {

    /**
     * data : {"b":["51738|12|12|0|114.4787843770673|30.504588404763545","51050|12|18|0|114.43662488231722|30.50547483054721"],"l":{"b":"2018-07-22 11:16:55","e":"珞雄路公交场站","i":"027-536-1","i2":"027-536-0","n":"536","p":"1.0~2.0","s":"九峰三路未来科技城","t1":"6:40","t2":"20:40"},"s":[{"i":"027-1198","m":"","n":"九峰三路未来科技城","o":1,"j":114.53372324782517,"w":30.490300750241953},{"i":"027-294","j":114.53372324782517,"m":"","n":"光谷七路九峰三路","o":2,"w":30.490300750241953},{"i":"027-2012","j":114.5314889342674,"m":"","n":"高新大道光谷七路","o":3,"w":30.488189631974883},{"i":"027-I-3337","j":114.52504378901486,"m":"","n":"高新大道高科园二路","o":4,"w":30.488608046963407},{"i":"027-91738","j":114.51754636879873,"m":"","n":"高新大道高科园路","o":5,"w":30.488559549055925},{"i":"027-383","j":114.51053761067205,"m":"","n":"高新大道中国十五冶","o":6,"w":30.489061509648245},{"i":"027-I-4048","j":114.49884509519804,"m":"","n":"九峰一路光谷公共服务中心","o":7,"w":30.493137886280536},{"i":"027-92213","j":114.49257991614829,"m":"","n":"光谷四路九峰一路","o":8,"w":30.490618615242187},{"i":"027-92061","j":114.49232833351994,"m":"","n":"光谷四路三星巷","o":9,"w":30.508793702024423},{"i":"027-92210","j":114.48563880660967,"m":"","n":"玉合巷光谷四路口","o":10,"w":30.50388660726258},{"i":"027-92207","j":114.48058438267684,"m":"","n":"孟新路玉合巷","o":11,"w":30.501083707601854},{"i":"027-I-3333","j":114.47655142660386,"m":"","n":"森林大道石门峰公园","o":12,"w":30.506385877203098},{"i":"027-91438","j":-0.006500294994953461,"m":"","n":"森林大道青王路","o":13,"w":-0.006000236193640239},{"i":"027-119","j":114.4629221428,"m":"","n":"珞喻东路高坡店","o":14,"w":30.50899574818192},{"i":"027-2767","j":114.44925215034478,"m":"","n":"珞喻东路长山","o":15,"w":30.50997350649571},{"i":"027-1684","j":114.44476861785293,"m":"","n":"珞喻东路油篓口","o":16,"w":30.508421086858416},{"i":"027-2769","j":114.43904772941026,"m":"","n":"珞喻东路森林公园","o":17,"w":30.505572734486808},{"i":"027-867","j":114.43226364510153,"m":"","n":"珞喻东路佳园路","o":18,"w":30.5054603803958},{"i":"027-2771","j":114.4275741939103,"m":"","n":"珞喻东路大黄村","o":19,"w":30.505674700059703},{"i":"027-2772","j":114.41902340154655,"m":"","n":"珞喻东路叶麻店","o":20,"w":30.506553002271446},{"i":"027-508","j":114.41162777160059,"m":"","n":"珞喻路关山口","o":21,"w":30.507216913488463},{"i":"027-2002","j":114.4075333539222,"m":"","n":"珞喻路湖北省中医院","o":22,"w":30.507012325785553},{"i":"027-1089","m":"","n":"珞雄路世界城广场","o":23},{"i":"027-91214","j":114.40605451767715,"m":"","n":"珞雄路公交场站","o":24,"w":30.501762098840913}]}
     * resultCode : 1
     * resultDes :
     */

    private DataBean data;
    private String resultCode;
    private String resultDes;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDes() {
        return resultDes;
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }

    public static class DataBean {
        /**
         * b : ["51738|12|12|0|114.4787843770673|30.504588404763545","51050|12|18|0|114.43662488231722|30.50547483054721"]
         * l : {"b":"2018-07-22 11:16:55","e":"珞雄路公交场站","i":"027-536-1","i2":"027-536-0","n":"536","p":"1.0~2.0","s":"九峰三路未来科技城","t1":"6:40","t2":"20:40"}
         * s : [{"i":"027-1198","m":"","n":"九峰三路未来科技城","o":1},{"i":"027-294","j":114.53372324782517,"m":"","n":"光谷七路九峰三路","o":2,"w":30.490300750241953},{"i":"027-2012","j":114.5314889342674,"m":"","n":"高新大道光谷七路","o":3,"w":30.488189631974883},{"i":"027-I-3337","j":114.52504378901486,"m":"","n":"高新大道高科园二路","o":4,"w":30.488608046963407},{"i":"027-91738","j":114.51754636879873,"m":"","n":"高新大道高科园路","o":5,"w":30.488559549055925},{"i":"027-383","j":114.51053761067205,"m":"","n":"高新大道中国十五冶","o":6,"w":30.489061509648245},{"i":"027-I-4048","j":114.49884509519804,"m":"","n":"九峰一路光谷公共服务中心","o":7,"w":30.493137886280536},{"i":"027-92213","j":114.49257991614829,"m":"","n":"光谷四路九峰一路","o":8,"w":30.490618615242187},{"i":"027-92061","j":114.49232833351994,"m":"","n":"光谷四路三星巷","o":9,"w":30.508793702024423},{"i":"027-92210","j":114.48563880660967,"m":"","n":"玉合巷光谷四路口","o":10,"w":30.50388660726258},{"i":"027-92207","j":114.48058438267684,"m":"","n":"孟新路玉合巷","o":11,"w":30.501083707601854},{"i":"027-I-3333","j":114.47655142660386,"m":"","n":"森林大道石门峰公园","o":12,"w":30.506385877203098},{"i":"027-91438","j":-0.006500294994953461,"m":"","n":"森林大道青王路","o":13,"w":-0.006000236193640239},{"i":"027-119","j":114.4629221428,"m":"","n":"珞喻东路高坡店","o":14,"w":30.50899574818192},{"i":"027-2767","j":114.44925215034478,"m":"","n":"珞喻东路长山","o":15,"w":30.50997350649571},{"i":"027-1684","j":114.44476861785293,"m":"","n":"珞喻东路油篓口","o":16,"w":30.508421086858416},{"i":"027-2769","j":114.43904772941026,"m":"","n":"珞喻东路森林公园","o":17,"w":30.505572734486808},{"i":"027-867","j":114.43226364510153,"m":"","n":"珞喻东路佳园路","o":18,"w":30.5054603803958},{"i":"027-2771","j":114.4275741939103,"m":"","n":"珞喻东路大黄村","o":19,"w":30.505674700059703},{"i":"027-2772","j":114.41902340154655,"m":"","n":"珞喻东路叶麻店","o":20,"w":30.506553002271446},{"i":"027-508","j":114.41162777160059,"m":"","n":"珞喻路关山口","o":21,"w":30.507216913488463},{"i":"027-2002","j":114.4075333539222,"m":"","n":"珞喻路湖北省中医院","o":22,"w":30.507012325785553},{"i":"027-1089","m":"","n":"珞雄路世界城广场","o":23},{"i":"027-91214","j":114.40605451767715,"m":"","n":"珞雄路公交场站","o":24,"w":30.501762098840913}]
         */

        private LBean l;
        private List<String> b;
        private ArrayList<SBean> s;

        public LBean getL() {
            return l;
        }

        public void setL(LBean l) {
            this.l = l;
        }

        public List<String> getB() {
            return b;
        }

        public void setB(List<String> b) {
            this.b = b;
        }

        public ArrayList<SBean> getS() {
            return s;
        }

        public void setS(ArrayList<SBean> s) {
            this.s = s;
        }

        public static class LBean {
            @Override
            public String toString() {
                return "LBean{" +
                        "e='" + e + '\'' +
                        ", s='" + s + '\'' +
                        '}';
            }

            /**
             * b : 2018-07-22 11:16:55
             * e : 珞雄路公交场站
             * i : 027-536-1
             * i2 : 027-536-0
             * n : 536
             * p : 1.0~2.0
             * s : 九峰三路未来科技城
             * t1 : 6:40
             * t2 : 20:40
             */

            private String b;
            private String e;
            private String i;
            private String i2;
            private String n;
            private String p;
            private String s;
            private String t1;
            private String t2;

            public String getB() {
                return b;
            }

            public void setB(String b) {
                this.b = b;
            }

            public String getE() {
                return e;
            }

            public void setE(String e) {
                this.e = e;
            }

            public String getI() {
                return i;
            }

            public void setI(String i) {
                this.i = i;
            }

            public String getI2() {
                return i2;
            }

            public void setI2(String i2) {
                this.i2 = i2;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public String getP() {
                return p;
            }

            public void setP(String p) {
                this.p = p;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public String getT1() {
                return t1;
            }

            public void setT1(String t1) {
                this.t1 = t1;
            }

            public String getT2() {
                return t2;
            }

            public void setT2(String t2) {
                this.t2 = t2;
            }
        }

        public static class SBean {
            /**
             * i : 027-1198
             * m :
             * n : 九峰三路未来科技城
             * o : 1
             * j : 114.53372324782517
             * w : 30.490300750241953
             */

            private String i;
            private String m;
            private String n;
            private int o;
            private double j;
            private double w;

            public String getI() {
                return i;
            }

            public void setI(String i) {
                this.i = i;
            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public int getO() {
                return o;
            }

            public void setO(int o) {
                this.o = o;
            }

            public double getJ() {
                return j;
            }

            public void setJ(double j) {
                this.j = j;
            }

            public double getW() {
                return w;
            }

            public void setW(double w) {
                this.w = w;
            }

            @Override
            public String toString() {
                return "SBean{" +
                        "n='" + n + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "BusLineInfo{" +
                "data=" + data +
                ", resultCode='" + resultCode + '\'' +
                ", resultDes='" + resultDes + '\'' +
                '}';
    }
}
