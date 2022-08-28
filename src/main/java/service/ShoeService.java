package service;

import bean.Shoe;
import Impl.ShoeServiceImpl;

import java.util.List;

/**
 * The service layer interface describes a set of methods for working with objects of the {@link Shoe} class.
 *
 * @author Miroslav Khotinskiy (merikbest2015@gmail.com)
 * @version 1.0
 * @see Shoe
 * @see ShoeServiceImpl
 */
public interface ShoeService {
    /**
     * Retrieves an Perfume by its id.
     *
     * @param id must not be null.
     * @return the Perfume with the given id.
     */
    Shoe getOne(Long id);

    /**
     * Return list of all perfumes.
     *
     * @return list of {@link Shoe}.
     */
    List<Shoe> findAll();

    /**
     * Returns list of perfumes which has the same perfume manufacturers, perfume genders and
     * the price is in the range between of starting price and ending price with the value of
     * the input parameter.
     *
     * @param perfumers perfume manufacturers to return.
     * @param genders   perfume genders to return.
     * @param prices    perfume price range
     */
    List<Shoe> filter(List<String> shoes, List<String> genders, List<Integer> prices);

    /**
     * Returns list of perfumes which has the same perfume manufacturer with the value of the input parameter.
     *
     * @param perfumer perfume manufacturer to return.
     * @return list of {@link Shoe}.
     */
    List<Shoe> findByShoeOrderByPriceDesc(String shoe);

    /**
     * Returns list of perfumes which has the same gender with the value of the input parameter.
     *
     * @param perfumeGender perfume gender to return.
     * @return list of {@link Shoe}.
     */
    List<Shoe> findByShoeGenderOrderByPriceDesc(String shoeGender);

    /**
     * Save updated perfume.
     *
     * @param perfumeTitle          perfume title to update.
     * @param perfumer              perfume manufacturer to update.
     * @param year                  the year the perfume was released to update.
     * @param country               manufacturer country to update.
     * @param perfumeGender         gender to update to update.
     * @param fragranceTopNotes     fragrance top notes to update.
     * @param fragranceMiddleNotes  fragrance middle notes to update.
     * @param fragranceBaseNotes    fragrance base notes to update.
     * @param description           perfume description to update.
     * @param filename              perfume image to update.
     * @param price                 perfume price to update.
     * @param volume                perfume volume to update.
     * @param type                  type of fragrance to update.
     * @param id                    the unique code of the perfume to update.
     */
    void saveProductInfoById(String shoeTitle, String shoe, Integer year, String country, String shoeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);

    /**
     * Save perfume info.
     *
     * @param perfume perfume object to return.
     * @return The {@link Shoe} class object which will be saved in the database.
     */
    Shoe save(Shoe shoe);
}