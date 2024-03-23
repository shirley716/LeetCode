class Solution {
    
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        //restrictions
        boolean [] output = new boolean[requests.length];
        int[] id = new int[n];
        for(int i=0; i<n;i++) id[i]=i;

        //iterate requests
        int reqLen = requests.length;
        int restrLen = restrictions.length;
        for(int i=0; i<reqLen;i++){
            //find parent of first element and second element
            int firstRequest = find(requests[i][0],id);
            int secondRequest = find(requests[i][1],id);
            //if have the same parent then
            if(firstRequest==secondRequest){
                output[i]=true;
                continue;
            }
            boolean flag=true;
            //iterate through restrictions
            for(int j=0; j<restrLen;j++){
                //find the parent of first restrict value and second
                int firstRestr = find(restrictions[j][0],id);
                int secondRestr = find(restrictions[j][1],id);
                // if parent of restrict and request  are equal then false
                if(firstRequest==firstRestr && secondRequest==secondRestr|| firstRequest==secondRestr&& secondRequest==firstRestr)
                {
                    flag=false;
                    break;
                }
            }
            if(flag){
                    output[i]=true;
                    id[firstRequest] = secondRequest;

            }else output[i]=false;
        }

        return output;
    }

    public int find(int p,int[] id){
        while(p!=id[p]) p = id[p];
        return p;
    }
}
