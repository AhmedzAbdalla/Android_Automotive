class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int ans=0,temp=0;
        for(auto i:gain){
            temp+=i;
            ans=max(ans,temp);
        }
        return ans;
    }
};