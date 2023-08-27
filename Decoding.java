import java.net.*;
import java.io.*;
import java.util.*;
import java.security.SecureRandom;

class HelloWorld 
{
  public static int strToInt(String s) 
  {
    int ans = 0;
    for (int i = 0; i < s.length(); i++) 
    {
      ans = ans + ((int) s.charAt(i) - (int)'0') * (int) Math.pow(10, s.length() - i - 1);
    }
    return ans;
  }
  public static String convert(int ka) 
  {
    StringBuilder s = new StringBuilder();
    int count = 0;
    while (ka != 0) 
    {
      int x = ka % 10;
      if (x > 5) 
      {
        x = x - 5;
      }
      s.insert(0, x);
      ka = ka / 10;
      count++;
    }
    return s.toString();
  }
  public static int generateRandomPrime(int lowerBound, int upperBound) 
  {
    SecureRandom random = new SecureRandom();
    int g;
    boolean isPrime;
    do 
    {
      g = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
      isPrime = true; // Check if the random number is prime 
      for (int i = 2; i <= Math.sqrt(g); i++) 
      { 
        if (g % i == 0) 
        { 
          isPrime = false; 
          break; 
        }
    }
  }
  while (!isPrime);
  return g;
}
public static int modPow(int base, int exponent, int modulus) 
  {
  int result = 1;
  base = base % modulus;
  while (exponent > 0) 
  {
    if (exponent % 2 == 1) 
    {
      result = (result * base) % modulus;
    }
    exponent = exponent >> 1;
    base = (base * base) % modulus;
  }
  return result;
}
public static String reverse(String s) 
  {
  StringBuilder ans = new StringBuilder();
  for (int i = s.length() - 1; i >= 0; i--) 
  {
    ans.append(s.charAt(i));
  }
  return ans.toString();
}
public static String columnShifter(String s, String rand) 
  {
  StringBuilder result = new StringBuilder(s);
  for (int i = 0; i < rand.length(); i = i + 2) 
  {
    int ind1 = Character.getNumericValue(rand.charAt(i));
    int ind2 = Character.getNumericValue(rand.charAt(i + 1));
    char temp = result.charAt(ind1);
    result.setCharAt(ind1, result.charAt(ind2));
    result.setCharAt(ind2, temp);
  }
  return result.toString();
}
public static String complement(String s) 
  {
  StringBuilder ans = new StringBuilder();
  for (int i = 0; i < s.length(); i++) 
  {
    if (s.charAt(i) == '1') 
    {
      ans.append('0');
    } 
    else 
    {
      ans.append('1');
    }
  }
  return ans.toString();
}
public static int binToDec(String s) 
  {
  int ans = 0;
  for (int i = 0; i < s.length(); i++) 
  {
    if (s.charAt(i) == '1') 
    {
      ans = ans + (int) Math.pow(2, 6 - i);
    }
  }
  return ans;
}
public static void main(String[] args) 
  {
  try 
    {
    ServerSocket ss = new ServerSocket(8888);
    Socket s = ss.accept(); // Initializing buffered reader to take input from the client 
      BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); // Initializing printstream To Send a message to the client 
      PrintStream ps = new PrintStream(s.getOutputStream()); // Scanner is used to take input from the compiler 
      Scanner sc = new Scanner(System.in); 
      int lowerBound = 100; 
      int upperBound = 999; 
      StringBuilder key = new StringBuilder(); 
      for (int i = 0; i < 2; i++) 
      { 
        String str3 = br.readLine(); 
        int G = strToInt(str3); 
        SecureRandom rand = new SecureRandom(); 
        String str2 = br.readLine(); 
        int n = strToInt(str2); 
        int b = rand.nextInt(100); 
        int y = modPow(G, b, n); 
        String str = br.readLine(); 
        int x = strToInt(str);
        ps.println(y);
    int kb = modPow(x, b, n) % 900 + 100;
    key.insert(0, convert(kb));
  }
  Vector < String > v = new Vector < > ();
  int x = 1;
  try 
    {
    while (x == 1) 
    {
      String fstr = br.readLine();
      if (fstr.equals("0")) 
      {
        break;
      }
      v.add(fstr);
    }
  } 
  catch (Exception e) 
    {
    x = 0;
  }
  key = new StringBuilder(reverse(key.toString()));
  StringBuilder fans = new StringBuilder();
  for (int i = 0; i < v.size(); i++) 
  {
    if (i % 2 != 0) 
    {
      String s1 = reverse(v.get(i));
      s1 = columnShifter(s1, key.toString());
      s1 = complement(s1);
      int ans = binToDec(s1);
      if ((char) ans == '@') 
      {
        ans = ' ';
      }
      fans.append((char) ans);
    }
  }
  System.out.println(fans.toString());
} 
  catch (Exception e) 
    {
  System.out.println(e);
}
}
}
