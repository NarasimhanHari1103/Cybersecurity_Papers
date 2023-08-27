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
    while (ka != 0) 
    {
      int x = ka % 10;
      if (x > 5) 
      {
        x = x - 5;
      }
      s.insert(0, x);
      ka = ka / 10;
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
public static String decToBinary(int val) 
  {
  StringBuilder s = new StringBuilder();
  while (val != 0) 
  {
    int a = val % 2;
    s.insert(0, a);
    val = val / 2;
  }
  return s.toString();
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
public static String randomString() 
  {
  StringBuilder ans = new StringBuilder();
  SecureRandom rand = new SecureRandom();
  for (int i = 0; i < 7; i++) 
  {
    int num = rand.nextInt(2);
    ans.insert(0, num);
  }
  return ans.toString();
}
public static String keyGenerator() 
  {
  StringBuilder ans = new StringBuilder();
  SecureRandom rand = new SecureRandom();
  for (int i = 0; i < 6; i++) 
  {
    int num = rand.nextInt(6);
    ans.insert(0, num);
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
public static String reverse(String s) 
  {
  return new StringBuilder(s).reverse().toString();
}
public static void main(String[] args) 
  {
  try 
    {
    Socket s = new Socket("localhost", 8888); // Initializing buffered reader to take input from the server 
    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); // Initializing printstream To Send a message to the server 
    PrintStream ps = new PrintStream(s.getOutputStream()); // Scanner is used to take input from the compiler 
    Scanner sc = new Scanner(System.in);
    int lowerBound = 100;
    int upperBound = 999;
    StringBuilder key = new StringBuilder();
    for (int i = 0; i < 2; i++) 
    {
      int G = generateRandomPrime(lowerBound, upperBound);
      ps.println(G);
      SecureRandom rand = new SecureRandom();
      int n = rand.nextInt(G + 10);
      ps.println(n);
      int a = rand.nextInt(100);
      int x = modPow(G, a, n);
      ps.println(x);
      String string = br.readLine();
      int y = strToInt(string);
      int ka = modPow(y, a, n) % 900 + 100;
      key.insert(0, convert(ka));
    }
    Vector < String > v = new Vector < > ();
    System.out.println("Enter a message: ");
    String str = sc.nextLine();
    for (int i = 0; i < str.length(); i++) 
    {
      int val = (int) str.charAt(i);
      String s1 = decToBinary(val);
      s1 = complement(s1);
      String rand = randomString();
      s1 = columnShifter(s1, key.toString());
      s1 = reverse(s1);
      v.add(rand);
      v.add(s1);
    }
    for (int i = 0; i < v.size(); i++) 
    {
      ps.println(v.get(i));
    }
    ps.println("0");
  } 
  catch (Exception e) 
  {
    System.out.println(e);
  }
}
}
