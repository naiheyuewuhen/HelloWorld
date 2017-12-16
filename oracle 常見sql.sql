

select concat('��','��') from dual



select rownum from dual connect by rownum<=10


select ascii('a') from dual  --97  ���ascii�X��

select abs(-10)  from dual 
select ln(2) from dual
select ceil(6.2) from dual -- ��^�j��ε���x���̤p���
select mod(8,3) from dual  -- �D�l�� 
select power(2,10) from dual   -- �D2��10 ����  1024   
select sqrt(25) from dual    -- �}���� 5

select cast(1 as varchar2(10)) from dual    -- �j������
-- convert
select to_char(1) from dual   -- �নvarchar2 ���� 
select to_char(0.456,'99990.99999') from dual   --   0.45600  �e�䦳�Ů�   format�Ѽ� 

select to_char(0.456,'FM99990.99999') from dual   --  FM �h��format�Ѽ� ��^���G�e�᪺�Ů�

select to_number('123.2531') from dual    -- �Nxxx�ର �r�Ŧ�

-- ���h��F��  ^ �}�l   $ ����
--  regexp_like()
-- regexp_replace()
-- regexp_substr() 

-- group by  �Z�� having    ����զZ����i�����L�o
-- order by �Ƨ�

select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') from dual   --  ����ର�r�Ŧ�
select to_date('2017-10-27 11:01:00','YYYY-MM-DD HH24:MI:SS') from dual   -- �r�Ŧ� ���� 

select add_months( to_date('2017-10-27 11:01:00','YYYY-MM-DD HH24:MI:SS'),-6) from dual  --  �[-6�Ӥ�

select last_day(sysdate) from dual   --  ��^�뤤���̫�@��

select current_date from dual   -- ��^��e�ɶ�

describe TEST_FOR_ZHAOTAO   --�d�ݪ��c 

----------------------------------------
union      -- ��^�d�ߥX���Ҧ���A���]�A���Ʀ� �ö�
union all  --��^�d�ߥX���Ҧ���A�]�A���Ʀ�    
intersect  --��^�d�ߥX���@����   �D�涰
minus      --��^�ĤG�Ӭd�ߥX����q�Ĥ@�Ӭd�ߥX���椤��h����ѤU����   �Y�D�t��

-- case when then else end

--�Ƭd�� connect by prior    start with        -- level �O���C  ���ڸ`�I��^1  �ڸ`�I���l�`�I��^2    




select * from SYSTEM_DEPT_FILE    --01040300

--  �q�U��W
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01040301' connect by DEPTMARK=prior HIGHERUPDEPTMARK
/* ���G  
1    01040301    ��q�[�uA��    01040300
2    01040300    ��q�[�u��    01040000
3    01040000    ���[�u�G��    01000000
4    01000000    �_�ʼҨ�t    00000000
5    00000000    �Ҫv��s�y�`�B    
*/

--  �q�ڸ`�I�}�l �V�U�d  00000000
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='00000000' connect by prior DEPTMARK=HIGHERUPDEPTMARK
order by level
/*  ���G
1    00000000    �Ҫv��s�y�`�B    
2    01000000    �_�ʼҨ�t    00000000
2    03000000    �Y�{���ݼҨ�t    00000000
2    02000000    �ٷ��Ҩ�t    00000000
3    01010000    �]�p��    01000000
3    01090000    �ҭ׽�    01000000
3    01020000    CSI �޳N��o��    01000000
3    01030000    ���[�u�@��    01000000
3    01040000    ���[�u�G��    01000000
3    01050000    �Ҩ�~�޳�    01000000
3    01070000    ESI��    01000000
3    01100000    SFC�t�ζ}�o��    01000000
3    01110000    WTC�Ҩ�t    01000000
3    01060000    �Ҩ��y�X�u��    01000000
3    01120000    �L�׼Ҩ�t    01000000
3    01990000    �_�I��L    01000000
3    01930000    XX�[�u��    01000000
3    03010000    �[�u�@��    03000000
3    03020000    �[�u�G��    03000000
3    02110000    ��Һ��׳�    02000000
3    02910000    �_�I�Ҩ��D�[�u��    02000000
3    02920000    �ݫ��Ҩ��D�[�u��    02000000
3    02100000    �ʫ��Ҫv��A�Ȥ���    02000000
3    02010000    �Ҩ�u�{��    02000000
3    02020000    �Ҩ�]�p��    02000000
3    02030000    �˪v��[�u��    02000000
3    02040000    �Ҩ�[�u��    02000000
3    02050000    �Ͳ��޲z��    02000000
3    02060000    �R�Ҩ�y��    02000000
3    02070000    ��ҹX�u�@��    02000000
3    02080000    ��ҹX�u�G��    02000000
3    02090000    �v��ե߳�    02000000
*/


-- SFC�t�ζ}�o��
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01100000' connect by prior DEPTMARK=HIGHERUPDEPTMARK
order by level
/*
1    01100000    SFC�t�ζ}�o��    01000000
2    01100200    SFC�t�ζ}�o���G��    01100000
2    01100100    SFC�t�ζ}�o���@��    01100000
*/

select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01100100' connect by DEPTMARK=prior HIGHERUPDEPTMARK
order by level


-----------------------------------------------------------------------------
--   GRANT  ���v   eg�G GRANT create session,create user,create table TO steve
--  REVOKE �����t�α��v  eg:  REVOKE create table FROM steve 
select * from user_sys_privs   -- �ˬd�t�η�e�Τ᪺�t�ίS�v  FOXYSHOP    UNLIMITED TABLESPACE    YES  ��e�v���L����




--  �����c eg:
declare
    width integer;
    height integer:=2;
    area  integer;
begin
    area:=6;
    width:=area/height;
    dbms_output.put_line('width='||width);
exception
    when zero_divide then
    dbms_output.put_line('Division by zero');
end;
/

-- 1  �ө� �C��
/*
cursor cursor_name  is  -- �C�ЦW��
select statement;       --�C�� �� �d�߻y�y


-- eg:
cursor cv_product_cursor is
    select product_id,name,price
    from products
    order by product_id;

--2  ���}�C�� open  ���}�C�ЦZ select�y�y �~ �u������
OPEN  cv_product_cursor

-- 3 �q�C�Ф����O�� FETCH
FETCH cursor_name
INTO variable  ;      --  �o�O�e��ө����ܶq select�y�y������ �N�s�b�o���ܶq��

eg�G
FETCH  cv_product_cursor
INTO v_product_id,v_name,v_price;  �C�Хi��]�t�h���O��   �ݭn�`��Ū�X

-- 4  �����C�� CLOSE
CLOSE cv_product_cursor;
*/

/*  �m��eg�G

CREATE OR REPLACE PROCEDURE test_for_zhaotao_p IS
-- �w�q�ܶq
p_testid TEST_FOR_ZHAOTAO.TESTID%type;
p_textname TEST_FOR_ZHAOTAO.TEXTNAME%type;
p_testage TEST_FOR_ZHAOTAO.TESTAGE%type;
p_testaddress TEST_FOR_ZHAOTAO.TESTADDRESS%type;
-- �ө��C��
cursor TEST_FOR_ZHAOTAO_cursor 
is 
    select testid,textname,testage,testaddress from TEST_FOR_ZHAOTAO;
BEGIN
    -- ���}�C��
    open TEST_FOR_ZHAOTAO_cursor;
    
    loop 
        fetch TEST_FOR_ZHAOTAO_cursor 
        into p_testid,p_textname,p_testage,p_testaddress;
        
        -- �`������ ��������
        exit when TEST_FOR_ZHAOTAO_cursor%notfound;
        dbms_output.put_line('id='||p_testid || 'name=' || p_textname || 'age=' || p_testage || 'address=' || p_testaddress );
        
    end loop;

    -- �����C��
    close TEST_FOR_ZHAOTAO_cursor;
   
   EXCEPTION
       WHEN OTHERS THEN
       RAISE;
END test_for_zhaotao_p;

*/
/* ����
BEGIN 
  FOXYSHOP.TEST_FOR_ZHAOTAO_P;
  DBMS_OUTPUT.Put_Line('');
  COMMIT; 
END;

--  ���G
id=2  name=���|  age=11  address=�Ѭz
id=3  name=�i�T  age=12  address=�e�_
id=4  name=����  age=20  address=�_�ʴI�h�d
*/

-- ��ƻP�s�x�L�{�ܬۦ�  �ߤ@�ϧO�N�O��ƥ����V�եΪ̪�^�@�ӭ� 
/*  �Ыب�� 
create or replace function circle_Area_zhaotao (p_radius in number) return number as
v_pi number :=3.1415926;
v_area number;
begin
    v_area:=v_pi*power(p_radius,2); 
    return v_area;
          
    exception
     WHEN OTHERS
   THEN
      RAISE;
end circle_Area_zhaotao;

-----------�ե�----------------
select circle_Area_zhaotao(2) from dual    --  ���G12.5663704
*/




















