import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game
{
    private java.util.stream.Collectors Collectors;
    class Team
    {
        String name;
        String city;

        public Team(String teamA, String cityA)
        {
            name = teamA;
            city = cityA;
        }
    }
    class Player
    {
        String name;
        String position;
        Team team;

        public Player(String s, String forward, Team team1)
        {
            name = s;
            position = forward;
            team = team1;
        }
    }
    class Match
    {
        LocalDate date;
        Team team1;
        Team team2;
        List<Player> players;
        public Match(LocalDate date, Team team1, Team team2, List<Player> players)
        {
            this.date = date;
            this.team1 = team1;
            this.team2 = team2;
            this.players = players;
        }
        public LocalDate getDate()
        {
            return date;
        }
        public void setDate(LocalDate date)
        {
            this.date = date;
        }
        public Team getTeam1()
        {
            return team1;
        }
        public void setTeam1(Team team1)
        {
            this.team1 = team1;
        }
        public Team getTeam2()
        {
            return team2;
        }
        public void setTeam2(Team team2)
        {
            this.team2 = team2;
        }
        public List<Player> getPlayers()
        {
            return players;
        }
        public void setPlayers(List<Player> players)
        {
            this.players = players;
        }
    }
    public void main(String[] args)
    {
        Team team1 = new Team("Team A", "City A");
        Team team2 = new Team("Team B", "City B");
        Player player1 = new Player("Player 1", "Forward", team1);
        Player player2 = new Player("Player 2", "Midfielder", team1);
        Player player3 = new Player("Player 3", "Defender", team2);
        Player player4 = new Player("Player 4", "Goalkeeper", team1);
        Match match1 = new Match(LocalDate.of(2024, 11, 8), team1, team2, List.of(player1, player2, player3));
        Match match2 = new Match(LocalDate.of(2024, 11, 9), team1, team2, List.of(player1, player2, player4));
        Match match3 = new Match(LocalDate.of(2024, 11, 10), team1, team2, List.of(player1, player2));
        Match match4 = new Match(LocalDate.of(2024, 11, 11), team1, team2, List.of(player2, player3, player4));
        List<Match> matches = List.of(match1, match2, match3, match4);
        Map<String, List<Player>> result = matches.stream()
                .flatMap(match -> match.players.stream())
                .collect(Collectors.groupingBy(
                        player -> player.team.name,
                        Collectors.groupingBy(
                                player -> player.name,
                                Collectors.counting()
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .filter(e -> e.getValue() > 3)
                                .map(Map.Entry::getKey)
                                .map(name -> new Player(name, "", new Team(entry.getKey(), "")))
                                .collect(Collectors.toList())
                ));
        result.forEach((teamName, players) ->
        {
            System.out.println("Team: " + teamName);
            players.forEach(player -> System.out.println(" - " + player.name));
        });
    }
}
