def tanx(states_needed,stations):
    # 最终选择的电台
    final_stations = set()
    
    while states_needed:
        best_station = None
        states_covered = set()
        # station-->kone   states_for_station-->["id","nv","ut"]
        for station,states_for_station in stations.items():
            covered = states_needed&states_for_station
            if len(states_covered) < len(covered):
                states_covered = covered
                best_station = station
        states_needed -= states_covered
        final_stations.add(best_station)
    print(final_stations)
    
def main():
    # 需要覆盖的地区
    states_needed = set(["mt","wa","or","id","nv","ut","ca","az"])
    stations = {}
    stations["kone"] = set(["id","nv","ut"])
    stations["ktwo"] = set(["wa","id","mt"])
    stations["kthree"] = set(["or","nv","ca"])
    stations["kfour"] = set(["nv","ut"])
    stations["kfive"] = set(["ca","az"])
    tanx(states_needed,stations)
        


    
if __name__ == "__main__":
    main()
 