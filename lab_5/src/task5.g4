grammar task5;

start: (X | Y)+EOF;

X:((ZERO | ONE ((ZERO | ONE ONE * ZERO )ZERO ) * (ZERO | ONE ONE * ZERO )ONE ) * ONE ((ZERO | ONE ONE *ZERO )ZERO ) * ){System.out.print("00");};
Y:((ZERO | ONE ((ZERO | ONE ONE * ZERO )ZERO ) * (ZERO | ONE ONE * ZERO )ONE ) * ONE ((ZERO | ONE ONE *ZERO )ZERO ) *ONE ONE * ){System.out.print("11");};

ZERO:'0';
ONE:'1';