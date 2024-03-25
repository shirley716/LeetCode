class Solution{
    boolean[] prime;
    int [] id;
    List<Integer>[]adj;;
    long nonPrime[] ;
    long primePathTotal = 0L;

    public long countPaths(int n, int[][] edges) {
        prime = eratosthenes(n);
        id = new int [n+1];
        adj=new ArrayList[n+1];
        nonPrime = new long[n+1];

        for(int i=0;i<=n;i++) {
            id[i] = i;
            adj[i] = new ArrayList<>();
        }
            //initiate 0 to n value;
        //iterate edges and update parent info to id
        for(int i=0; i<edges.length;i++){
            int p=edges[i][0];
            int q=edges[i][1];
            //find all the nearby point for each of point
            adj[p].add(q);
            adj[q].add(p);
            // union
            if(!isPrime(p) && !isPrime(q))
                if(find(p)!=find(q))
                    union(p,q);
        }

        //update the total of number to parent for all non-prime number

        int key;
        for(int i=1;i<=n;i++){
            key = find(i);//fidn key
            nonPrime[key]++;
        }


        //Iterate through all nearly by non prime point to see how may m1 for each of it;

        long total=0,pathValue=0;
        ArrayList<Long> path;

        //iterate through the arrayList and id list
        for(int j=1; j<=n;j++){
            total=0;
            pathValue=0;
            if(!prime[j]) continue;
            path = new ArrayList<>();
            for(int nearestVal :adj[j])
                if(!isPrime(nearestVal)) {
                    long value = nonPrime[find(nearestVal)];
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
        while(x!=id[x])
            x=id[x];
        return x;
    }
    //Union
    void union(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i>j) id[i] = j;
        else id[j]=i;
    }

    //to see all prime numbers for num<=n;
    boolean[] eratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and
        // initialize all entries it as true. A value in
        // prime[i] will finally be false if i is Not a
        // prime, else true.
        boolean prime[] = new boolean[n + 1];
        prime[0]=prime[1]=false;
        for (int i = 2; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it is a
            // prime
            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }


        return prime;
    }
    // prime return boolean
    boolean isPrime(int x){
        return prime[x];
    }


}
