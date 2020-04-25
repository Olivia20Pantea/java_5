package com.company;
import java.util.Scanner;
import java.util.Random;
public class Menu {
    public  void menu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Meniu:");
        System.out.println("C - initializeaza tabloul de sortat cu valori generate aleator");
        System.out.println("V - vizualizeaza tablou");
        System.out.println("R - reinitializeaza tabloul: pentru aducerea tabloului la forma initiala dupa ce acesta a fost sortat, in vederea aplicarii unei alte sortari");
        System.out.println("S - sortare shellsort");
        System.out.println("H - sortare heapsort");
        System.out.println("Q - sortare quicksort");
        System.out.println("X - parasirea programului");
        char c;
        int n;
        long start, stop;
        System.out.print("\n"+"Introduceti numarul de elemente pe care doriti sa le generati aleator: ");
        n=scanner.nextInt();
        int[] v=new int[n];
        int[] a=new int[n];
        do{
            System.out.print("\n"+"Alegeti o optiune din meniu: ");
            c=scanner.next().charAt(0);
            c=Character.toUpperCase(c);
            switch(c)
            {
                case 'C':
                    initializare(n, a, v);
                    break;
                case 'V':
                    afisare(n, a);
                    break;
                case 'R':
                    reinitializare(n, v, a);
                    break;
                case 'S':
                    start=System.currentTimeMillis();
                    shellSort(a,n);
                    System.out.println("Ati ales sa sortati tabloul cu ajutorul sortarii shellsort");
                    stop=System.currentTimeMillis();
                    System.out.println("Sortarea dureaza: "+(stop-start));
                    break;
                case 'H':
                    start = System.currentTimeMillis();
                    heapSort(a,n);
                    System.out.println("Ati ales sa sortati tabloul cu ajutorul sortarii heapsort");
                    stop=System.currentTimeMillis();
                    System.out.println("Sortarea dureaza: "+(stop-start));
                    break;
                case 'Q':
                    start=System.currentTimeMillis();
                    quicksort(a,0,n-1);
                    System.out.println("Ati ales sa sortati tabloul cu ajutorul sortarii quicksort");
                    stop=System.currentTimeMillis();
                    System.out.println("Sortarea dureaza: "+(stop-start));
                    break;
                case 'X':
                    System.out.println("Ati ales sa iesiti.");
                    break;
                default:
                    System.out.println("Aceasta optiune nu se afla in meniu");
            }
        }while(c != 'X');
    }

    public void initializare(int n, int[] a, int[] v)
    {
        int i;
        Random rand = new Random();
        for(i=0;i<n;i++)
            v[i]=a[i]=rand.nextInt(1000);
    }
    public void afisare(int n, int[] a)
    {
        int i;
        for(i=0;i<n;i++)
            System.out.println("\t"+"a["+(i+1)+"]="+a[i]);
    }
    public void reinitializare(int n, int[] v, int[] a)
    {
        int i;
        for(i=0;i<n;i++)
            a[i]=v[i];
    }
    public void shellSort(int[] a, int n)
    {
        int gap,temp,i,j;
        for (gap=n/2;gap>0;gap=gap/2)
            for (i=gap;i<n;i++)
            {
                temp=a[i];
                for(j=i;j>=gap&&a[j-gap]>temp;j=j-gap)
                    a[j]=a[j-gap];
                a[j]=temp;
            }
    }
    public void heapify(int[] a, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2
        // If left child is larger than root
        if (l < n && a[l] > a[largest])
            largest = l;
        // If right child is larger than largest so far
        if (r < n && a[r] > a[largest])
            largest = r;
        // If largest is not root
        if (largest != i)
        {
            int aux=a[i];
            a[i]=a[largest];
            a[largest]=aux;
            // Recursively heapify the affected sub-tree
            heapify(a, n, largest);
        }
    }
    public void heapSort(int[] a, int n)
    {
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int aux=a[0];
            a[0]=a[i];
            a[i]=aux;
            // call max heapify on the reduced heap
            heapify(a, i, 0);
        }
    }
    void quicksort(int[] a, int s,int d)
    {
        int mij=(s+d)/2;
        int x=a[mij];
        int i=s,j=d;
        while(i<=j)
        {
            while(a[i]<x)
                i++;
            while(a[j]>x)
                j--;
            if (i<=j)
            {
                int aux=a[i];
                a[i]=a[j];
                a[j]=aux;
                i++;
                j--;
            }
        }
        if (i<d)
            quicksort(a,i,d);
        if (j>s)
            quicksort(a,s,j);
    }
}