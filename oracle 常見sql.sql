

select concat('趙','濤') from dual



select rownum from dual connect by rownum<=10


select ascii('a') from dual  --97  獲取ascii碼值

select abs(-10)  from dual 
select ln(2) from dual
select ceil(6.2) from dual -- 返回大於或等於x的最小整數
select mod(8,3) from dual  -- 求餘數 
select power(2,10) from dual   -- 求2的10 次冪  1024   
select sqrt(25) from dual    -- 開平方 5

select cast(1 as varchar2(10)) from dual    -- 強轉類型
-- convert
select to_char(1) from dual   -- 轉成varchar2 類型 
select to_char(0.456,'99990.99999') from dual   --   0.45600  前邊有空格   format參數 

select to_char(0.456,'FM99990.99999') from dual   --  FM 去除format參數 返回結果前後的空格

select to_number('123.2531') from dual    -- 將xxx轉為 字符串

-- 正則表達式  ^ 開始   $ 結束
--  regexp_like()
-- regexp_replace()
-- regexp_substr() 

-- group by  后用 having    對分組后的行進行條件過濾
-- order by 排序

select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') from dual   --  日期轉為字符串
select to_date('2017-10-27 11:01:00','YYYY-MM-DD HH24:MI:SS') from dual   -- 字符串 轉日期 

select add_months( to_date('2017-10-27 11:01:00','YYYY-MM-DD HH24:MI:SS'),-6) from dual  --  加-6個月

select last_day(sysdate) from dual   --  返回月中的最後一天

select current_date from dual   -- 返回當前時間

describe TEST_FOR_ZHAOTAO   --查看表結構 

----------------------------------------
union      -- 返回查詢出的所有行，不包括重複行 並集
union all  --返回查詢出的所有行，包括重複行    
intersect  --返回查詢出的共有行   求交集
minus      --返回第二個查詢出的行從第一個查詢出的行中減去之後剩下的行   即求差集

-- case when then else end

--數查詢 connect by prior    start with        -- level 是偽列  對於根節點返回1  根節點的子節點返回2    




select * from SYSTEM_DEPT_FILE    --01040300

--  從下到上
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01040301' connect by DEPTMARK=prior HIGHERUPDEPTMARK
/* 結果  
1    01040301    放電加工A組    01040300
2    01040300    放電加工課    01040000
3    01040000    機加工二部    01000000
4    01000000    北京模具廠    00000000
5    00000000    模治具製造總處    
*/

--  從根節點開始 向下查  00000000
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='00000000' connect by prior DEPTMARK=HIGHERUPDEPTMARK
order by level
/*  結果
1    00000000    模治具製造總處    
2    01000000    北京模具廠    00000000
2    03000000    廊坊金屬模具廠    00000000
2    02000000    濟源模具廠    00000000
3    01010000    設計部    01000000
3    01090000    模修課    01000000
3    01020000    CSI 技術研發部    01000000
3    01030000    機加工一部    01000000
3    01040000    機加工二部    01000000
3    01050000    模具品管部    01000000
3    01070000    ESI課    01000000
3    01100000    SFC系統開發部    01000000
3    01110000    WTC模具廠    01000000
3    01060000    模具制造鉗工部    01000000
3    01120000    印度模具廠    01000000
3    01990000    北富其他    01000000
3    01930000    XX加工部    01000000
3    03010000    加工一部    03000000
3    03020000    加工二部    03000000
3    02110000    塑模維修部    02000000
3    02910000    北富模具厂加工部    02000000
3    02920000    �搦側狳蓖D加工部    02000000
3    02100000    晉城模治具服務中心    02000000
3    02010000    模具工程部    02000000
3    02020000    模具設計部    02000000
3    02030000    檢治具加工部    02000000
3    02040000    模具加工部    02000000
3    02050000    生產管理部    02000000
3    02060000    沖模制造部    02000000
3    02070000    塑模鉗工一部    02000000
3    02080000    塑模鉗工二部    02000000
3    02090000    治具組立部    02000000
*/


-- SFC系統開發部
select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01100000' connect by prior DEPTMARK=HIGHERUPDEPTMARK
order by level
/*
1    01100000    SFC系統開發部    01000000
2    01100200    SFC系統開發部二課    01100000
2    01100100    SFC系統開發部一課    01100000
*/

select level,DEPTMARK,DEPTNAME,HIGHERUPDEPTMARK 
from SYSTEM_DEPT_FILE
start with DEPTMARK='01100100' connect by DEPTMARK=prior HIGHERUPDEPTMARK
order by level


-----------------------------------------------------------------------------
--   GRANT  授權   eg： GRANT create session,create user,create table TO steve
--  REVOKE 取消系統授權  eg:  REVOKE create table FROM steve 
select * from user_sys_privs   -- 檢查系統當前用戶的系統特權  FOXYSHOP    UNLIMITED TABLESPACE    YES  當前權限無限制




--  塊結構 eg:
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

-- 1  申明 遊標
/*
cursor cursor_name  is  -- 遊標名稱
select statement;       --遊標 的 查詢語句


-- eg:
cursor cv_product_cursor is
    select product_id,name,price
    from products
    order by product_id;

--2  打開遊標 open  打開遊標后 select語句 才 真正執行
OPEN  cv_product_cursor

-- 3 從遊標中取記錄 FETCH
FETCH cursor_name
INTO variable  ;      --  這是前邊申明的變量 select語句中的值 將存在這些變量中

eg：
FETCH  cv_product_cursor
INTO v_product_id,v_name,v_price;  遊標可能包含多條記錄   需要循環讀出

-- 4  關閉遊標 CLOSE
CLOSE cv_product_cursor;
*/

/*  練習eg：

CREATE OR REPLACE PROCEDURE test_for_zhaotao_p IS
-- 定義變量
p_testid TEST_FOR_ZHAOTAO.TESTID%type;
p_textname TEST_FOR_ZHAOTAO.TEXTNAME%type;
p_testage TEST_FOR_ZHAOTAO.TESTAGE%type;
p_testaddress TEST_FOR_ZHAOTAO.TESTADDRESS%type;
-- 申明遊標
cursor TEST_FOR_ZHAOTAO_cursor 
is 
    select testid,textname,testage,testaddress from TEST_FOR_ZHAOTAO;
BEGIN
    -- 打開遊標
    open TEST_FOR_ZHAOTAO_cursor;
    
    loop 
        fetch TEST_FOR_ZHAOTAO_cursor 
        into p_testid,p_textname,p_testage,p_testaddress;
        
        -- 循環取值 結束條件
        exit when TEST_FOR_ZHAOTAO_cursor%notfound;
        dbms_output.put_line('id='||p_testid || 'name=' || p_textname || 'age=' || p_testage || 'address=' || p_testaddress );
        
    end loop;

    -- 關閉遊標
    close TEST_FOR_ZHAOTAO_cursor;
   
   EXCEPTION
       WHEN OTHERS THEN
       RAISE;
END test_for_zhaotao_p;

*/
/* 執行
BEGIN 
  FOXYSHOP.TEST_FOR_ZHAOTAO_P;
  DBMS_OUTPUT.Put_Line('');
  COMMIT; 
END;

--  結果
id=2  name=李四  age=11  address=天津
id=3  name=張三  age=12  address=河北
id=4  name=王五  age=20  address=北京富士康
*/

-- 函數與存儲過程很相似  唯一區別就是函數必須向調用者返回一個值 
/*  創建函數 
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

-----------調用----------------
select circle_Area_zhaotao(2) from dual    --  結果12.5663704
*/




















