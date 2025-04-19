def solution(nodes, edges):
    n = len(nodes)
    
    idx = {}
    for i in range(n):
        idx[nodes[i]] = i
    
    adj = [[] for _ in range(n)]
    deg = [0] * n
    
    for e in edges:
        u, v = idx[e[0]], idx[e[1]]
        adj[u].append(v)
        adj[v].append(u)
        deg[u] += 1
        deg[v] += 1
    
    ans = [0, 0]
    vis = [False] * n
    
    for i in range(n):
        if vis[i]:
            continue
        
        q = [i]
        comp = []
        vis[i] = True
        
        while q:
            u = q.pop(0)
            comp.append(u)
            
            for v in adj[u]:
                if not vis[v]:
                    vis[v] = True
                    q.append(v)
        
        zero_count = 0
        one_count = 0
        
        for u in comp:
            val_parity = nodes[u] & 1 
            deg_parity = deg[u] & 1    
            
            c = val_parity ^ deg_parity 
            if c == 0:
                zero_count += 1
            else:
                one_count += 1
        

        if zero_count == 1:
            ans[0] += 1
        if one_count == 1:
            ans[1] += 1
    
    return ans