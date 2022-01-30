package DataProcessingWithStreams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Competition {

    public static Map<String, Integer> getTeamPlaceMap(Collection<Team> teams) {
        // write your code here
        return teams.stream()
                .sorted((o1, o2) -> Integer.compare(o1.getPlace(), o2.getPlace()))
                .collect(Collectors.toMap(Team::getName, Team::getPlace, (f, s) -> f, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Team> teams = Stream
                .iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine().split("\\s+"))
                .map(parts -> new Team(parts[0], Integer.parseInt(parts[1])))
                .collect(Collectors.toSet());

        getTeamPlaceMap(teams)
                .forEach((team, speaker) -> System.out.println(team + ": " + speaker));
    }
}

class Team {
    private final String name;
    private final int place;

    public Team(String name, int place) {
        this.name = name;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }
}