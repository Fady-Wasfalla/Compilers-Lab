grammar lab10;
start : x EOF {System.out.println($x.val);};
x returns [int val]
: NUMBER {$val = Integer.parseInt($NUMBER.text,2);};
NUMBER : ('0' .. '9') + ;
RES : [\r\n\t] + -> skip;