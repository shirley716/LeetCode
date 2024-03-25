class Solution {
   ArrayList<Integer> primes ;
    int [] id;
    ArrayList<ArrayList<Integer>> next;;
    ArrayList<Long> nonPrime ;
    long primePathTotal = 0;

    public long countPaths(int n, int[][] edges) {
        primes = eratosthenes(n);
        id = new int [n+1];
        next=new ArrayList<>();
        nonPrime = new ArrayList<>();

        for(int i=0;i<=n;i++) {
            id[i] = i;
            next.add(new ArrayList<>());
            nonPrime.add(0L);
        }
            //initiate 0 to n value;
        //iterate edges and update parent info to id
        for(int i=0; i<edges.length;i++){
            int p=edges[i][0];
            int q=edges[i][1];
            //find all the nearby point for each of point
            next.get(p).add(q);
            next.get(q).add(p);
            // union
            if(!isPrime(p) && !isPrime(q))
                if(find(p)!=find(q))
                    union(p,q);
        }

        //update the total of number to parent for all non-prime number

        int key;
        for(int i=1;i<=n;i++){
            key = id[i];
            nonPrime.set(key,nonPrime.get(key)+1);
        }
        for(int i=1;i<=n;i++){
            key = id[i];
            nonPrime.set(i,nonPrime.get(key));
        }

        //Iterate through all nearly by non prime point to see how may m1 for each of it;

        int prime,total=0,pathValue=0;
        ArrayList<Long> path;

        //iterate through the arrayList and id list
        for(int j=0; j<primes.size();j++){
            total=0;
            pathValue=0;
            prime=primes.get(j);
            path = new ArrayList<>();
            for(int nearestVal :next.get(prime))
                if(!isPrime(nearestVal)) {
                    long value = nonPrime.get(nearestVal);
                    path.add(value);
                    total+= value;
                }


            for(int v = 0; v<path.size();v++)
                pathValue += (total-path.get(v))*path.get(v);

            primePathTotal+=pathValue/2+total;

        }
        return primePathTotal;
    }

    //find
    int find(int x){
        if(x!=id[x])
            id[x]=find(id[x]);
        return id[x];
    }
    //Union
    void union(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i>j) id[i] = j;
        else id[j]=i;
    }

    //to see all prime numbers for num<=n;
    ArrayList<Integer> eratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and
        // initialize all entries it as true. A value in
        // prime[i] will finally be false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        ArrayList<Integer> arraylist = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                arraylist.add(i);
        }
        return arraylist;
    }
    // prime return boolean
    boolean isPrime(int x){
        return primes.contains(x);
    }

    //prime number
}

